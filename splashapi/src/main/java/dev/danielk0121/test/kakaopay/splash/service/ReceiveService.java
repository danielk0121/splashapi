package dev.danielk0121.test.kakaopay.splash.service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.kakaopay.splash.dao.SplashDao;
import dev.danielk0121.test.kakaopay.splash.dao.SplashReceiveDao;
import dev.danielk0121.test.kakaopay.splash.domain.Splash;
import dev.danielk0121.test.kakaopay.splash.domain.SplashReceive;
import dev.danielk0121.test.kakaopay.splash.dto.ResponseError;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveResponseDto;
import lombok.extern.slf4j.Slf4j;

/**
 * 받기 서비스
 */
@Slf4j
@Service
public class ReceiveService {

	@Autowired private SplashDao splashDao;
	@Autowired private SplashReceiveDao splashReceiveDao;
	
	@Autowired private ExpireTimeService expireTimeSerivce;
	
	/**
	 * createUserId-token-roomId 에 해당하는 splash seq 를 조회
	 * 		찾는 splash 가 없는 경우 에러처리 	=> 에러4 처리
	 * 		10분이 지난 경우 제외 				=> 에러1 처리
	 * 
	 * splashSeq 에 해당하는 splash_receive 중 seq 순으로 리스트 조회
	 * 		뿌리기 자기 자신인 경우 제외 			=> 에러2 처리
	 * 		이미 받은 경우 					=> 에러3 처리
	 * 
	 * 조회 결과가 있으면, splash_receive 1건 점령(업데이트)
	 * 		받기 할 건이 남아있지 않는 경우		=> 에러5 처리
	 */
	public ReceiveResponseDto process(ReceiveRequestDto req) {
		
		Splash splash = splashDao.selectOneSplashByCreateUserIdAndTokenAndRoomId(
				Splash.builder()
					.createUserId(req.getCreateUserId())
					.token(req.getToken())
					.roomId(req.getMeta().getRoomId())
					.build()
				);
		log.debug("select, splash: {}", splash);
		
		if(splash == null) {
			return createErrorResponse(ResponseError.ERRCODE_recv004, ResponseError.ERRMSG_recv004);
		}
		if(splash.getCreateUserId() == req.getMeta().getUserId()) {
			return createErrorResponse(ResponseError.ERRCODE_recv002, ResponseError.ERRMSG_recv002);
		}
		if(isCheckSplashExpired(splash.getCreatedDate())) {
			return createErrorResponse(ResponseError.ERRCODE_recv001, ResponseError.ERRMSG_recv001);
		}
		
		List<SplashReceive> splashReceiveList = splashReceiveDao.selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq(
				SplashReceive.builder()
				.splashSeq(splash.getSeq())
				.build()) ;
		log.debug("select, splashReceiveList: {}", splashReceiveList);
		
		if(isAlreadyReceived(splashReceiveList, req.getMeta().getUserId())) {
			return createErrorResponse(ResponseError.ERRCODE_recv003, ResponseError.ERRMSG_recv003);
		}
		
		SplashReceive splashReceive = findUpdateItem(splashReceiveList);
		log.debug("find, splashReceive: {}", splashReceive);
		if(splashReceive == null) {
			return createErrorResponse(ResponseError.ERRCODE_recv005, ResponseError.ERRMSG_recv005);
		}
		
		int updateCnt = updateSplashReceive(splashReceive, req.getMeta().getUserId());  // 받기 완료, db update
		log.debug("update, updateCnt: {}, splashReceive: {}", updateCnt, splashReceive);
		
		ReceiveResponseDto res = ReceiveResponseDto.builder()
				.receiveAmount(splashReceive.getReceiveAmount())
				.build() ;
		
		return res;
	}
	
	/**
	 * now 가 (createdDate + 10분) 지나면 true, else false
	 */
	public boolean isCheckSplashExpired(Date createdDate) {
		
		return expireTimeSerivce.isCheckSplashExpired(
				createdDate, 10, ChronoUnit.MINUTES);
	}
	
	private boolean isAlreadyReceived(List<SplashReceive> splashReceiveList, long userId) {

		for(var item : splashReceiveList) {
			if(StringUtils.equals("Y", item.getFinishYn())
					&& userId == item.getReceiveUserId()) {
				return true;
			}
		}
		return false;
	}
	
	private SplashReceive findUpdateItem(List<SplashReceive> splashReceiveList) {
		
		for(var item : splashReceiveList) {
			if(StringUtils.equals("Y", item.getFinishYn())) {
				continue;
			}
			return item;
		}
		return null;
	}
	
	private int updateSplashReceive(SplashReceive param, long userId) {
		
		param.setFinishYn("Y");
		param.setReceiveUserId(userId);
		return splashReceiveDao.updateSplashReceive(param);
	}
	
	private ReceiveResponseDto createErrorResponse(String errorCode, String errorMessage) {

		log.debug("errorCode: {}, errorMessage: {}", errorCode, errorMessage);
		return ReceiveResponseDto.builder()
				.error(ResponseError.builder()
						.errorMessage(errorMessage)
						.errorCode(errorCode)
						.build())
				.build() ;
	}
}

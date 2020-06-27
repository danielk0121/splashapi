package dev.danielk0121.test.kakaopay.splash.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchResponseDto;
import dev.danielk0121.test.kakaopay.splash.dto.search.SplashReceiveStatus;
import dev.danielk0121.test.kakaopay.splash.dto.search.SplashStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * 조회 서비스
 */
@Slf4j
@Service
public class SearchService {

	@Autowired private SplashDao splashDao;
	@Autowired private SplashReceiveDao splashReceiveDao;
	
	@Autowired private ExpireTimeService expireTimeSerivce;
	
	/**
	 * <pre>
	 * 뿌리기 토큰을 요청으로 받음
	 *   토큰에 해당하는 현재 상태를 응답
	 *     현재상태 : 
	 *       - 뿌리기 생성 시간
	 *       - 뿌리기 금액
	 *       - 받기 완료된 금액 합계
	 *       - 받기 완료된 정보 리스트
	 *         - 받기 사용자 id
	 *         - 받기 금액
	 * 
	 * 에러 처리
	 *   - 뿌리기 생성 사용자 id 만 조회 가능 => createUserId 를 파라미터로 받지 않으면, 검사 불필요
	 *   - 조회 유효기간 7일 제한
	 *   - 존재하지 않는 뿌리기 건
	 * </pre>
	 */
	public SearchResponseDto process(SearchRequestDto req) {
		
		Splash splash = splashDao.selectOneSplashByCreateUserIdAndTokenAndRoomId(
				Splash.builder()
					.createUserId(req.getMeta().getUserId())
					.token(req.getToken())
					.roomId(req.getMeta().getRoomId())
					.build()
				);
		log.debug("select, splash: {}", splash);
		
		if(splash == null) {
			return createErrorResponse(ResponseError.ERRCODE_srch003, ResponseError.ERRMSG_srch003);
		}

		// 항상 true, createUserId 를 파라미터로 받지 않으면, 검사 불필요
//		if(splash.getCreateUserId() != req.getMeta().getUserId()) {
//			return createErrorResponse(ResponseError.ERRCODE_srch001, ResponseError.ERRMSG_srch001);
//		}
		
		if(isCheckSplashExpired(splash.getCreatedDate())) {
			return createErrorResponse(ResponseError.ERRCODE_srch002, ResponseError.ERRMSG_srch002);
		}
		
		List<SplashReceive> splashReceiveList = splashReceiveDao
				.selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq(
						SplashReceive.builder()
							.splashSeq(splash.getSeq())
							.build()
						) ;
		log.debug("select, splashReceiveList: {}", splashReceiveList);
		
		SearchResponseDto res = SearchResponseDto.builder()
				.splashStatus(SplashStatus.builder()
						.createUserId(splash.getCreateUserId())
						.token(splash.getToken())
						.roomId(splash.getRoomId())
						.splashAmount(splash.getSplashAmount())
						.createdDate(splash.getCreatedDate())
						.splashFinishAmount(getSplashFinishAmount(splashReceiveList))
						.build())
				.splashReceiveStatusList(getSplashReceiveStatusList(splashReceiveList))
				.build() ;
		
		return res;
	}
	
	private double getSplashFinishAmount(List<SplashReceive> splashReceiveList) {
		
		double splashFinishAmount = 0;
		for(var item : splashReceiveList) {
			if(StringUtils.equals("Y", item.getFinishYn())) {
				splashFinishAmount += item.getReceiveAmount();
			}
		}
		return splashFinishAmount;
	}
	
	private List<SplashReceiveStatus> getSplashReceiveStatusList(List<SplashReceive> splashReceiveList) {

		List<SplashReceiveStatus> list = new ArrayList<>();
		for(var item : splashReceiveList) {
			if(StringUtils.equals("Y", item.getFinishYn())) {
				list.add(SplashReceiveStatus.builder()
						.receiveUserId(item.getReceiveUserId())
						.receiveAmount(item.getReceiveAmount())
						.build());
			}
		}
		return list;
	}
	
	/**
	 * now 가 (createdDate + 7일) 지나면 true, else false
	 */
	private boolean isCheckSplashExpired(Date createdDate) {
		
		return expireTimeSerivce.isCheckSplashExpired(
				createdDate, 7, ChronoUnit.DAYS);
	}
	
	private SearchResponseDto createErrorResponse(String errorCode, String errorMessage) {

		log.debug("errorCode: {}, errorMessage: {}", errorCode, errorMessage);
		return SearchResponseDto.builder()
				.error(ResponseError.builder()
						.errorMessage(errorMessage)
						.errorCode(errorCode)
						.build())
				.build() ;
	}
}

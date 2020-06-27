package dev.danielk0121.test.kakaopay.splash.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.kakaopay.splash.dao.SplashDao;
import dev.danielk0121.test.kakaopay.splash.dao.SplashReceiveDao;
import dev.danielk0121.test.kakaopay.splash.domain.Splash;
import dev.danielk0121.test.kakaopay.splash.domain.SplashReceive;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashResponseDto;
import lombok.extern.slf4j.Slf4j;

/**
 * 뿌리기 서비스
 */
@Slf4j
@Service
public class SplashService {

	@Autowired private SplashDao splashDao;
	@Autowired private SplashReceiveDao splashReceiveDao;
	
	@Autowired private AmountService amountService;
	@Autowired private TokenService tokenService;
	
	/**
	 * 토큰생성
	 * splash 테이블 데이터 생성 - 단건
	 * splash_receive 테이블 데이터 생성 - 리스트
	 */
	public SplashResponseDto process(SplashRequestDto req) {
		
		String token = tokenService.createToken(req.getMeta().getUserId(), req.getMeta().getRoomId());
		insertSplash(req, token);
		insertSplashReceive(req, token);
		
		return SplashResponseDto.builder()
				.token(token)
				.build() ;
	}
	
	public void insertSplash(SplashRequestDto req, String token) {
		
		Splash param = Splash.builder()
				.createUserId(req.getMeta().getUserId())
				.roomId(req.getMeta().getRoomId())
				.token(token)
				.splashAmount(req.getSplashAmount())
				.splashUserCount(req.getSplashUserCount())
				.build();
		splashDao.insertSplash(param);
		log.debug("splash insert, insertBean: {}", param);
	}
	
	public void insertSplashReceive(SplashRequestDto req, String token) {
		
		// splash 테이블의 seq 찾음
		Splash param = Splash.builder()
				.createUserId(req.getMeta().getUserId())
				.roomId(req.getMeta().getRoomId())
				.token(token)
				.build() ;
		Splash bean = splashDao.selectOneSplashByCreateUserIdAndTokenAndRoomId(param);
		long splashSeq = bean.getSeq();
		log.debug("find splashSeq, splashSeq: {}, param: {}", splashSeq, param);
		
		// 금액 분배
		List<Double> splashAmountList = amountService.createSplashAmountList(
				req.getSplashAmount(), req.getSplashUserCount());
		
		List<SplashReceive> splashReceiveList = new ArrayList<>();
		for(var receiveAmount : splashAmountList) {
			SplashReceive splashReceive = SplashReceive.builder()
					.splashSeq(splashSeq)
					.finishYn("N")
					.receiveUserId(-1)  // 미배정
					.receiveAmount(receiveAmount)
					.build();
			splashReceiveList.add(splashReceive);
		}
		
		// insert 리스트
		splashReceiveDao.insertSplashReceiveList(splashReceiveList);
		log.debug("insert splashReceive list, splashReceiveList: {}", splashReceiveList);
	}
}

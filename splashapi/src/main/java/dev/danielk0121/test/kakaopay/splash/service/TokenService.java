package dev.danielk0121.test.kakaopay.splash.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.kakaopay.splash.dao.SplashDao;
import dev.danielk0121.test.kakaopay.splash.domain.Splash;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenService {

	@Autowired private SplashDao dao;
	
	/**
	 * TODO 유니크한 토큰 생성 방식 개선 필요
	 */
	public String createToken(long userId, String roomId) {

		int maxTry = 100_000;
		
		for(int i = 0; i < maxTry; i++) {
			String genTkn = generateRandomToken();
			Splash param = Splash.builder()
					.createUserId(userId)
					.roomId(roomId)
					.token(genTkn)
					.build() ;
			Splash bean = dao.selectOneSplashByCreateUserIdAndTokenAndRoomId(param);
			if(bean == null) {  // 디비에 저장된적 없는 토큰이면 사용 가능
				return genTkn;
			}
			log.debug("already used token, token: {}", genTkn);
		}
		
		throw new RuntimeException("can not create new token");
	}
	
	public String generateRandomToken() {
		List<String> list = new ArrayList<>();
		list.add(randUpper());
		list.add(randLower());
		list.add(randNumer());
		Collections.shuffle(list);
		return String.join("", list);
	}
	
	private String randUpper() {
		Random rnd = new Random();
		return String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
	}
	
	private String randLower() {
		Random rnd = new Random();
		return String.valueOf((char) ((int) (rnd.nextInt(26)) + 65));
	}
	
	private String randNumer() {
		Random rnd = new Random();
		return String.valueOf(rnd.nextInt(10));
	}
}

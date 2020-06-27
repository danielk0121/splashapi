package dev.danielk0121.test.kakaopay.splash.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.kakaopay.splash.domain.Splash;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SplashDaoTest {

	@Autowired private SplashDao dao;
	
//	@Test
	public void t0() {
		
		Splash bean = dao.selectOneSplashByCreateUserIdAndTokenAndRoomId(Splash.builder()
				.createUserId(100)
				.token("tk0")
				.roomId("rmid0")
				.build());
		log.debug("bean : {}", bean);
		assertThat(bean).isNotNull();
	}
}

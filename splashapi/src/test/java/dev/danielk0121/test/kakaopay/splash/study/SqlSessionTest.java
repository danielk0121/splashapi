package dev.danielk0121.test.kakaopay.splash.study;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.kakaopay.splash.domain.Splash;
import dev.danielk0121.test.kakaopay.splash.domain.SplashReceive;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class SqlSessionTest {

	@Autowired private SqlSessionTemplate sqlSessionTemplate;
	
//	@Test
	public void t0() {
		
		String now = sqlSessionTemplate.selectOne("selectNow", null);
		log.debug("now : {}", now);
	}
	
//	@Test
	public void t1() {
		
		List<Splash> beanList = sqlSessionTemplate.selectList("selectSplashListLimit3", null);
		log.debug("beanList: {}", beanList);
	}
	
//	@Test
	public void t2() {
		
		List<SplashReceive> beanList = sqlSessionTemplate.selectList("selectSplashReceiveListLimit3", null);
		log.debug("beanList: {}", beanList);
	}
	
//	@Test
	public void t3() {
		
		Splash bean = sqlSessionTemplate.selectOne("selectOneSplashByCreateUserIdAndTokenAndRoomId", Splash.builder()
				.createUserId(100)
				.token("tk0")
				.roomId("rmid01")
				.build());
		log.debug("bean: {}", bean);
	}
	
}

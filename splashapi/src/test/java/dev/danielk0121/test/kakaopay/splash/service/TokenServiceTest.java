package dev.danielk0121.test.kakaopay.splash.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class TokenServiceTest {

	@Autowired private TokenService service;
	
//	@Test
	public void t0() {
		for(int i = 0; i < 10; i++) {
			String token = service.createToken(100, "rmid0");
			log.debug("token : {}", token);
		}
	}
	
//	@Test
	public void t1() {
		for(int i = 0; i < 10; i++) {
			String token = service.generateRandomToken();
			log.debug("token : {}", token);
		}
	}
}

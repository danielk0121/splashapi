package dev.danielk0121.test.kakaopay.splash.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.kakaopay.splash.dto.RequestHeaderMeta;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveResponseDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ReceiveServiceTest {

	@Autowired ReceiveService service;
	
//	@Test
	public void t0() {
		
//		for(int userId = 2000; userId < 2020; userId++) {
			
			ReceiveRequestDto req = ReceiveRequestDto.builder()
					.meta(RequestHeaderMeta.builder()
							.userId(3001)
							.roomId("rmid100")
							.build())
					.createUserId(100)
					.token("T1b")
					.build() ;
			
			ReceiveResponseDto res = service.process(req);
			log.debug("req: {}, res: {}", req, res);
//		}
	}
	
//	@Test
	public void t1() {
		
		log.debug("" + service.isCheckSplashExpired(Date.from(LocalDateTime
				.parse("2020-06-27T04:50:00")
				.atZone(ZoneId.systemDefault())
				.toInstant())));
		log.debug("" + service.isCheckSplashExpired(Date.from(LocalDateTime
				.parse("2020-06-27T05:00:00")
				.atZone(ZoneId.systemDefault())
				.toInstant())));
	}
}

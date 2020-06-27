package dev.danielk0121.test.kakaopay.splash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.kakaopay.splash.dto.RequestHeaderMeta;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashResponseDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SplashServiceTest {

	@Autowired private TokenService tokenService;
	@Autowired private SplashService service;
	
//	@Test
	public void t0() {
		
		SplashRequestDto req = SplashRequestDto.builder()
				.meta(RequestHeaderMeta.builder()
						.userId(100)
						.roomId("rmid100")
						.build())
				.splashAmount(10000)
				.splashUserCount(12)
				.build() ;
		SplashResponseDto res = service.process(req);
		log.debug("req: {}, res: {}", req, res);
	}
	
//	@Test
	public void t1() {
		
		SplashRequestDto req = SplashRequestDto.builder()
				.meta(RequestHeaderMeta.builder()
						.userId(100)
						.roomId("rmid100")
						.build())
				.splashAmount(10000)
				.splashUserCount(12)
				.build() ;
		String token = tokenService.createToken(req.getMeta().getUserId(), req.getMeta().getRoomId());
		service.insertSplash(req, token);
	}
}

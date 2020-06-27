package dev.danielk0121.test.kakaopay.splash.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.kakaopay.splash.dto.RequestHeaderMeta;
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchResponseDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SearchServiceTest {

	@Autowired private SearchService service;
	
//	@Test
	public void t0() {
		
		SearchRequestDto req = SearchRequestDto.builder()
				.meta(RequestHeaderMeta.builder()
						.userId(100)
						.roomId("rmid100")
						.build())
				.token("T1b")
				.build() ;
		SearchResponseDto res = service.process(req);
		log.debug("req: {}, res: {}", req, res);
	}
}

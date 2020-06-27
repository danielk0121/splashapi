package dev.danielk0121.test.kakaopay.splash.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AmountServiceTest {

	@Autowired private AmountService service;
	
//	@Test
	public void t0() {
		
		double splashAmount = 10000.0;
		long splashUserCount = 12;
		
		for(int i = 0; i < 3; i++) {
			List<Double> list = service.createSplashAmountList(splashAmount, splashUserCount);
			double total = 0;
			for(double item : list) {
				total += item;
			}
			log.debug("splashAmount: {}, splashUserCount: {}, total: {}, list: {}"
					, splashAmount, splashUserCount, total, list);
			assertThat(total).isLessThanOrEqualTo(splashAmount);
		}
	}
}

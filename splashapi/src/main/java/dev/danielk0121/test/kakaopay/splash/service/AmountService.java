package dev.danielk0121.test.kakaopay.splash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AmountService {

	private static final double RATE1 = 0.7;
	private static final double RATE2 = 0.3;
	
	/**
	 * RATE1:RATE2 으로 나누고, RATE1는 균등분배, RATE2은 랜덤배분, 랜덤배분으로 인해 남은 금액을 랜덤선택해서 한명에게 올인
	 */
	public List<Double> createSplashAmountList(double splashAmount, long splashUserCount) {

		Random rand = new Random();
		
		double totalAmt = .0;
		double val1 = Math.floor(splashAmount * RATE1 / splashUserCount);
		double val2 = Math.floor(splashAmount * RATE2 / splashUserCount);
		List<Double> list = new ArrayList<>();
		
		// 분배
		for(int i = 0; i < splashUserCount; i++) {
			double randPer = rand.nextDouble();
			randPer = randPer < 0.1 ? 0.1 : randPer;  // 최소 10% 이상으로 설정
			randPer = Math.floor(randPer*10)/10.0;  // 소수 1자리 까지 허용
			double amt1 = val1;
			double amt2 = Math.floor(val2 * randPer);  // 소수점 절삭
			double amt = amt1 + amt2;
			totalAmt += amt;
			log.debug("randPer: {}, amt1: {}, amt2: {}, totalAmt: {}, "
					, randPer, amt1, amt2, totalAmt);
			list.add(amt);
		}
		
		// 나머지 처리
		double remainAmt = splashAmount - totalAmt;
		int len = list.size();
		
		// 나머지를 균등 분배 => 나머지가 커도 작아도 균등하게 추가 분배 되므로, 격차가 별로 안 벌어진다
//		if(remainAmt > 0) {
//			double val3 = Math.floor(remainAmt / splashUserCount);  // 소수점 절삭
//			for(int i = 0; i < splashUserCount; i++) {
//				log.debug("remainAmt: {}, val3: {}", remainAmt, val3);
//				list.set(i, list.get(i) + val3);
//			}
//		}
		
		// 격차를 벌어지도록 유도하기 위해, 나머지를 한명에게 올인
		int winIndex = rand.nextInt(len);
		double before = list.get(winIndex);
		double after = before + remainAmt;
		log.debug("remainAmt: {}, winIndex: {}, before: {}, after: {}"
				, remainAmt, winIndex, before, after);
		list.set(winIndex, after);
		
		return list;
	}
}

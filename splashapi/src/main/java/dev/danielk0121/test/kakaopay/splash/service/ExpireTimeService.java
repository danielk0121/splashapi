package dev.danielk0121.test.kakaopay.splash.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpireTimeService {

	/**
	 * now 가 (createdDate + amountToAdd) 지나면 true, else false
	 */
	public boolean isCheckSplashExpired(Date createdDate
			, long amountToAdd, ChronoUnit unit) {
		
		LocalDateTime createdDateTime = LocalDateTime.ofInstant(
				createdDate.toInstant(), ZoneId.systemDefault());
		LocalDateTime expireTime = createdDateTime.plus(amountToAdd, unit);
		LocalDateTime now = LocalDateTime.now();
		
		log.debug("createdDate: {}, createdDateTime: {}, expireTime: {}, now: {}"
				, createdDate, createdDateTime, expireTime, now);
		
		if(now.isAfter(expireTime)) {
			return true;
		}
		return false;
	}
}

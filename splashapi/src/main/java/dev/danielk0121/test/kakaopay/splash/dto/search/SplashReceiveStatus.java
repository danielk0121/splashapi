package dev.danielk0121.test.kakaopay.splash.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class SplashReceiveStatus {

	private long receiveUserId;
	private double receiveAmount;
	
	@Builder
	public SplashReceiveStatus(long receiveUserId, double receiveAmount) {
		super();
		this.receiveUserId = receiveUserId;
		this.receiveAmount = receiveAmount;
	}
}

package dev.danielk0121.test.kakaopay.splash.dto.search;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class SplashStatus {

	private long createUserId;
	private String token;
	private String roomId;
	
	private double splashAmount;
	
	@JsonFormat(timezone = "Asia/Seoul")
	private Date createdDate;
	
	private double splashFinishAmount;

	@Builder
	public SplashStatus(long createUserId, String token, String roomId, double splashAmount, Date createdDate,
			double splashFinishAmount) {
		super();
		this.createUserId = createUserId;
		this.token = token;
		this.roomId = roomId;
		this.splashAmount = splashAmount;
		this.createdDate = createdDate;
		this.splashFinishAmount = splashFinishAmount;
	}
}

package dev.danielk0121.test.kakaopay.splash.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class Splash {
	
	private long seq;
	
	private long createUserId;
	private String token;
	private String roomId;
	
	private double splashAmount;
	private long splashUserCount;
	private Date createdDate;
	
	@Builder
	public Splash(long seq, long createUserId, String token, String roomId, double splashAmount, long splashUserCount,
			Date createdDate) {
		super();
		this.seq = seq;
		this.createUserId = createUserId;
		this.token = token;
		this.roomId = roomId;
		this.splashAmount = splashAmount;
		this.splashUserCount = splashUserCount;
		this.createdDate = createdDate;
	}
}

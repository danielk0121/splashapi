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
public class SplashReceive {

	private long seq;
	
	private long splashSeq;
	private String finishYn;
	
	private long receiveUserId;
	private double receiveAmount;
	
	private Date regDate;
	private Date updDate;
	
	@Builder
	public SplashReceive(long seq, long splashSeq, String finishYn, long receiveUserId, double receiveAmount,
			Date regDate, Date updDate) {
		super();
		this.seq = seq;
		this.splashSeq = splashSeq;
		this.finishYn = finishYn;
		this.receiveUserId = receiveUserId;
		this.receiveAmount = receiveAmount;
		this.regDate = regDate;
		this.updDate = updDate;
	}
}

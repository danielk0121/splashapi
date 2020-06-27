package dev.danielk0121.test.kakaopay.splash.dto.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.danielk0121.test.kakaopay.splash.dto.ResponseError;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class ReceiveResponseDto {

	private ResponseError error;
	
	private double receiveAmount;

	@Builder
	public ReceiveResponseDto(ResponseError error, double receiveAmount) {
		super();
		this.error = error;
		this.receiveAmount = receiveAmount;
	}
}

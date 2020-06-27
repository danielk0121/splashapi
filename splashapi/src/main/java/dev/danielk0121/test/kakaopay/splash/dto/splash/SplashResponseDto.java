package dev.danielk0121.test.kakaopay.splash.dto.splash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class SplashResponseDto {

	private String token;

	@Builder
	public SplashResponseDto(String token) {
		super();
		this.token = token;
	}
}

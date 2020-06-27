package dev.danielk0121.test.kakaopay.splash.dto.splash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.danielk0121.test.kakaopay.splash.dto.RequestHeaderMeta;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class SplashRequestDto {

	private RequestHeaderMeta meta;
	
	private double splashAmount;
	private long splashUserCount;
	
	@Builder
	public SplashRequestDto(RequestHeaderMeta meta, double splashAmount, long splashUserCount) {
		super();
		this.meta = meta;
		this.splashAmount = splashAmount;
		this.splashUserCount = splashUserCount;
	}
}

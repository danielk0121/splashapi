package dev.danielk0121.test.kakaopay.splash.dto.search;

import java.util.List;

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
public class SearchResponseDto {

	private ResponseError error;
	
	private SplashStatus splashStatus;
	private List<SplashReceiveStatus> splashReceiveStatusList;
	
	@Builder
	public SearchResponseDto(ResponseError error, SplashStatus splashStatus,
			List<SplashReceiveStatus> splashReceiveStatusList) {
		super();
		this.error = error;
		this.splashStatus = splashStatus;
		this.splashReceiveStatusList = splashReceiveStatusList;
	}
}

package dev.danielk0121.test.kakaopay.splash.dto.search;

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
public class SearchRequestDto {

	private RequestHeaderMeta meta;
	
	private String token;
	
	@Builder
	public SearchRequestDto(RequestHeaderMeta meta, String token) {
		super();
		this.meta = meta;
		this.token = token;
	}
}

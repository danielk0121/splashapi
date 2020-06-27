package dev.danielk0121.test.kakaopay.splash.dto.receive;

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
public class ReceiveRequestDto {

	private RequestHeaderMeta meta;
	
	private long createUserId;
	private String token;
	
	@Builder
	public ReceiveRequestDto(RequestHeaderMeta meta, long createUserId, String token) {
		super();
		this.meta = meta;
		this.createUserId = createUserId;
		this.token = token;
	}
}

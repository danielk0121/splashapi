package dev.danielk0121.test.kakaopay.splash.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class RequestHeaderMeta {

	private long userId;
	private String roomId;
	
	@Builder
	public RequestHeaderMeta(long userId, String roomId) {
		super();
		this.userId = userId;
		this.roomId = roomId;
	}
}

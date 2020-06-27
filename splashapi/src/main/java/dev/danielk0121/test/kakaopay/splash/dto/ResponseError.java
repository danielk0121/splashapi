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
public class ResponseError {

	public static final String ERRCODE_recv001 = "recv001";
	public static final String ERRMSG_recv001 = "해당 뿌리기 건의 받기 유효기간이 만료되었습니다, 10분";
	
	public static final String ERRCODE_recv002 = "recv002";
	public static final String ERRMSG_recv002 = "자기 자신이 생성한 뿌리기 건은 받기 할 수 없습니다";
	
	public static final String ERRCODE_recv003 = "recv003";
	public static final String ERRMSG_recv003 = "이미 받은 뿌리기 건 입니다";
	
	public static final String ERRCODE_recv004 = "recv004";
	public static final String ERRMSG_recv004 = "존재하지 않는 뿌리기 건 입니다";
	
	public static final String ERRCODE_recv005 = "recv005";
	public static final String ERRMSG_recv005 = "더 이상 남은 받기 건이 없습니다";
	
	public static final String ERRCODE_srch001 = "srch001";
	public static final String ERRMSG_srch001 = "뿌리기 생성 사용자만 조회 가능합니다";
	
	public static final String ERRCODE_srch002 = "srch002";
	public static final String ERRMSG_srch002 = "해당 뿌리기 건의 조회 기간이 만료되었습니다, 7일";
	
	public static final String ERRCODE_srch003 = "srch003";
	public static final String ERRMSG_srch003 = "존재하지 않는 뿌리기 건 입니다";
	
	private String errorCode;
	private String errorMessage;
	
	@Builder
	public ResponseError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}

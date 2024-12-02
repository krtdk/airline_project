package edu.du.airline_project.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 서영
 * 결제 요청 시 반환받는 Dto
 */
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) 
public class PaymentResponseDto {

	private String tid;
	private String nextRedirectPcUrl;
	private String nextRedirectMobileUrl;
	private String nextRedirectAppUrl;
	private String androidAppScheme;
	private String iosAppScheme;
	private Timestamp createdAt;
	
}

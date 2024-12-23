package edu.du.airline_project.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *   
 * 환불 요청 시 필요한 Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovedCancelAmountDto {

	private Integer total;
	private Integer taxFree;
	private Integer vat;
	private Integer point;
	private Integer discount;
	private Integer greenDeposit;
	
}

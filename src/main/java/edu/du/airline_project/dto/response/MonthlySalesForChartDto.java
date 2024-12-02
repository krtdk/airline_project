package edu.du.airline_project.dto.response;

import lombok.Data;

/**
 *   
 * 특정 연도의 월간 매출액
 */
@Data
public class MonthlySalesForChartDto {

	private Integer year;
	private Integer month;
	private Long sales;
	
}

package edu.du.airline_project.dto.response;

import lombok.Data;

/**
 *   
 * 스케줄별 좌석의 기본 가격을 담을 DTO
 */
@Data
public class SeatPriceDto {

	private Long economyPrice;
	private Long businessPrice;
	private Long firstPrice;
	
}

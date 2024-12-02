package edu.du.airline_project.dto.request;

import lombok.Data;

/**
 *   
 * 메인 페이지에서 항공권 조회 시 받아오는 Dto
 */
@Data
public class ScheduleOptionForMainPageDto {

	private String ticketType;
	private String departure;
	private String destination;
	// 편도
	private String flightDate0;
	// 왕복
	private String flightDate1;
	private String flightDate2;
	
	private Integer adultCount;
	private Integer childCount;
	private Integer infantCount;
	
}

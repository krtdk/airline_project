package edu.du.airline_project.dto.response;

import lombok.Data;

/**
 *   
 * 도착지 이용객 수를 담는 Dto
 * 대시보드에서 사용
 */
@Data
public class DestinationCountDto {

	private String destination;
	private Integer count;
	
}

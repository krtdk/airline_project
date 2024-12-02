package edu.du.airline_project.dto.response;

import lombok.Data;

/**
 *    
 * 고객의 말씀 유형별 비율을 구하기 위해 사용하는 Dto
 */
@Data
public class VocCountByTypeDto {

	private String type;
	private Integer count;
}

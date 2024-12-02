package edu.du.airline_project.repository.model;

import lombok.Data;

/**
 *   
 * 고객의 말씀 답변 양식
 */
@Data
public class VocAnswerForm {

	private Integer id;
	private String type;
	private String content;
	
}

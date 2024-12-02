package edu.du.airline_project.repository.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @author 서영
 * 고객의 말씀 답변
 */
@Data
public class VocAnswer {

	private Integer id;
	@NotBlank
	private String content;
	private Timestamp createdAt;
	private Integer vocId;
	
}

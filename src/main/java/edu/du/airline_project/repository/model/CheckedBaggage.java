package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class CheckedBaggage {

	private Integer id;
	private String section;
	private String gradeId;
	private String freeAllowance;
	
}

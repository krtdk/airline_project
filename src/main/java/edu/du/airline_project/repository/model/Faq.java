package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class Faq {

	private Integer id;
	private String title;
	private String content;
	private Integer categoryId;
}

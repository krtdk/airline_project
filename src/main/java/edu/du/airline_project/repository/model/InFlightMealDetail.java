package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class InFlightMealDetail {

	private Integer id;
	private String name;
	private String description;
	private String image;
	private Integer mealId;
	
}

package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class RequestMeal {

	private Integer id;
	private Integer amount;
	private Integer mealDetailId;
	private String ticketId;
	
}

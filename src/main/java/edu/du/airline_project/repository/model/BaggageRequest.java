package edu.du.airline_project.repository.model;

import lombok.Data;

@Data	
public class BaggageRequest {

	private Integer id;
	private Integer amount;
	private String ticketId;
	private String memberId;
	
}

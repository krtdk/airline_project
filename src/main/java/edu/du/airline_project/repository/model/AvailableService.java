package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class AvailableService {

	private Integer id;
	private Integer flightHours;
	private Integer serviceId;
}
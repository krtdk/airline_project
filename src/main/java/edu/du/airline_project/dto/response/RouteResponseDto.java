package edu.du.airline_project.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RouteResponseDto {

	private String destination;
	private String departure;
	private String flightTime;
	private Timestamp departureDate;
	private Timestamp arrivalDate;
	
}

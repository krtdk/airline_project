package edu.du.airline_project.repository.model;

import lombok.Data;

import java.sql.Timestamp;

// 운항 일정
@Data
public class Schedule {

	private Integer id;
	private Timestamp departureDate;
	private Timestamp arrival_date;
	private Integer airplaneId;
	private Integer routeId;
	
}
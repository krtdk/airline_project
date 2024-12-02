package edu.du.airline_project.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleSelectDto {

	private String airport1;
	private String airport2;
	private Date flightDate1;
	private Date flightDate2;
	
}

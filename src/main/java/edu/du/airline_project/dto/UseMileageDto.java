package edu.du.airline_project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UseMileageDto {

	private int id;
	private Date useDate;
	private int useMileage;
	private String description;
	private int productPrice;
	private int hiddenCount;
	private String memberId;

	
}

package edu.du.airline_project.repository.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SaveMileage {

	private Date saveDate;
	private Timestamp expirationDate;
	private int saveMileage;
	private int balance;
	private String memberId;
}

package edu.du.airline_project.repository.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Gifticon {

	private Date startDate;
	private Date endDate;
	private int orderId;
}

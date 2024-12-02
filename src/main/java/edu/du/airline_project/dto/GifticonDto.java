package edu.du.airline_project.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class GifticonDto {

	private Date startDate;
	private Timestamp endDate;
	private int orderId;
	private int amount;
	private String brand;
	private String name;
	private String gifticonImage;
	private int id;
	private Date revokeDate;
	private int productId;
	private int count;
	private Long price;
}

package edu.du.airline_project.repository.model;

import lombok.Data;

@Data
public class ShopOrder {

	private int id;
	private int amount;
	private int productId;
	private String memberId;
	
}

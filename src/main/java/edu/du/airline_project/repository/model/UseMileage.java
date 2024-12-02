package edu.du.airline_project.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseMileage {

	private Long mileageFromBalance;
	private Integer buyMileageId;
	private Integer gifticonId;
	private String ticketId;
	
	public UseMileage(Long mileageFromBalance, Integer buyMileageId, String ticketId) {
		super();
		this.mileageFromBalance = mileageFromBalance;
		this.buyMileageId = buyMileageId;
		this.ticketId = ticketId;
	}
	
}

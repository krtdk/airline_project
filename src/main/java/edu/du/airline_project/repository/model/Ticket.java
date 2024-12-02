package edu.du.airline_project.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

// 예약된 티켓
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

	private String id;
	private Integer adultCount;
	private Integer childCount;
	private Integer infantCount;
	private String seatGrade;
	private String memberId;
	private Integer scheduleId;
	private Timestamp reservedDate;
	private Integer paymentType;
	
	public Ticket(Integer adultCount, Integer childCount, Integer infantCount, String seatGrade,
			Integer scheduleId) {
		this.adultCount = adultCount;
		this.childCount = childCount;
		this.infantCount = infantCount;
		this.seatGrade = seatGrade;
		this.scheduleId = scheduleId;
	}
	
	
	
}
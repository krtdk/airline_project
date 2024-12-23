package edu.du.airline_project.dto;

import edu.du.airline_project.utils.NumberUtil;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SaveMileageDto {

	private Timestamp departureDate;
	private Timestamp expirationDate;
	
	private int extinctionMileage;
	private Long saveMileage;
	private Long saveMileage2;
	private int balance;
	private Timestamp insertTime;
	private String memberId;
	private String ticketId;
	private String ticketId2;
	private Long amount1;
	private Long amount2;
	public Long totalMileage;
	public String balanceNumber() {
		return  NumberUtil.numberFormat(balance);
	}
	public String totalMileageNumber() {
		return  NumberUtil.numberFormat(totalMileage);
		
	}
}

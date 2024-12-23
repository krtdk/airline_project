package edu.du.airline_project.repository.model;

import edu.du.airline_project.utils.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mileage {

	private int id;
	private Timestamp mileageDate;
	private Long useMileage;
	private String description;
	private Timestamp expirationDate;
	private Long saveMileage;
	private Long balance;
	private String memberId;
	private String ticketId;
	
	// 삭제해야 함
	private Long mileageFromBalance;
	private Timestamp dateFormExpiration;
	private int productId;
	private int gifticonId;
	private int buyMileageId;
	private Integer status;
	
	
	
	public String balanceNumber() {
		return  NumberUtil.numberFormat(balance);
		
	}


	public Mileage(int id, Long balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	
	
	
	
}


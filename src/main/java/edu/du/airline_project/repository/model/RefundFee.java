package edu.du.airline_project.repository.model;

import edu.du.airline_project.utils.Define;
import edu.du.airline_project.utils.NumberUtil;
import lombok.Data;

/**
 *   
 */
@Data
public class RefundFee {

	private Integer criterion;
	private Integer type;
	private Long fee;
	
	public String formatFee() {
		return NumberUtil.numberFormat(fee);
	}
	
	public String formatMilesFee() {
		return NumberUtil.numberFormat((long) Math.floor(fee * Define.MILES_TICKET_RATE));
	}
	
}

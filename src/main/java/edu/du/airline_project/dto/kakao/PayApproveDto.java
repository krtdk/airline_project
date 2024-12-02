package edu.du.airline_project.dto.kakao;

import lombok.Data;

@Data
public class PayApproveDto {

	private String aid;
	private String tid;
	private AmountDto amount;
	
}

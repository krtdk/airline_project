package edu.du.airline_project.dto.response;

import edu.du.airline_project.utils.TimestampUtil;
import lombok.Data;

import java.sql.Timestamp;

/**
 *   
 */
@Data
public class MemberInfoDto {

	private String id;
	private String korName;
	private String engName;
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String email;
	private String address;
	private String nationality;
	private String grade;
	private Timestamp joinAt;
	private Timestamp withdrawAt;
	private Integer status;
	private String userRole;
	
	public String formatJoinAt() {
		return TimestampUtil.dateToString(joinAt);
	}
	
	public String formatWithdrawAt() {
		return TimestampUtil.dateToString(withdrawAt);
	}
	
}

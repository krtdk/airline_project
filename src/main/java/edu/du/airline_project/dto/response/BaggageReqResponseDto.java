package edu.du.airline_project.dto.response;

import edu.du.airline_project.utils.TimestampUtil;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaggageReqResponseDto {

	private Integer id;
	private String section;
	private String gradeId;
	private String freeAllowance;
	private Integer broId;
	private Integer routeId;
	private Integer baggageId;
	private Integer breId;
	private Integer amount;
	private String memberId;
	private Timestamp departureDate;
	private String ticketId;
	private String departure;
	private String destination;
	private Integer seatCount;
	private String seatGradeName;

	public String departureDateFormat() {
		return TimestampUtil.dateTimeToString(departureDate);
	}
}

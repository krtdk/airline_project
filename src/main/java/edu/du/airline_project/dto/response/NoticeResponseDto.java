package edu.du.airline_project.dto.response;

import edu.du.airline_project.utils.TimestampUtil;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NoticeResponseDto {

	private int id;
	private String title;
	private String content;
	private Timestamp createdAt;
	private int categoryId;
	private String name;
	private int ncId;
	
	public String dateFormat() {
		return TimestampUtil.dateTimeToString(createdAt);
	}
	
	// 날짜만 보이게
	public String dateFormatType2() {
		return TimestampUtil.dateToString(createdAt);
	}
}

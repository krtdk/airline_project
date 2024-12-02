package edu.du.airline_project.repository.model;

import edu.du.airline_project.utils.TimestampUtil;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Notice {

	private Integer id;
	private String title;
	private String content;
	private Timestamp createdAt;
	private Integer categoryId;
	
	public String dateFormat() {
		return TimestampUtil.dateTimeToString(createdAt);
	}
}

package edu.du.airline_project.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NoticeRequestDto {

	private Integer id;
	private String title;
	private String content;
	private Timestamp createdAt;
	private Integer categoryId;
	
}

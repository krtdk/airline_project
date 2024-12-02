package edu.du.airline_project.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FaqResponseDto {

	private Integer id;
	private String title;
	private String content;
	private Integer viewCount;
	private Timestamp createdAt;
	private Integer categoryId;
	private String name;

}

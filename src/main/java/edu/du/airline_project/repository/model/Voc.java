package edu.du.airline_project.repository.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class Voc {

	private Integer id;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String email;
	private String type;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private Integer categoryId;
	private String memberId;
	private String ticketId;
	private Timestamp createdAt;
	
}

package edu.du.airline_project.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManagerFormDto {
	
	@NotBlank
	private String managerName;
	private String birthDate;
	@NotBlank
	private String gender;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String email;
	
}

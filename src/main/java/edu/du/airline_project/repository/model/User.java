package edu.du.airline_project.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	private String id;
	private String password;
	private String userRole;
	private Timestamp joinAt;
	private Timestamp withdrawAt;
	private Integer status;
	
}
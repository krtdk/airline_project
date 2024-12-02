package edu.du.airline_project.repository.model;

import lombok.Data;

// 회원 등급
@Data
public class MemberGrade {

	private String name;
	private double mileageRate;
	private Long rankUpMileage;
	
}
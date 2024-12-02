package edu.du.airline_project.repository.model;

import lombok.Data;

import java.sql.Date;

// 회원
@Data
public class Member {

	private String id;
	private String korName;
	private String engName;
	private Date birthDate;
	private String gender;
	private String phoneNumber;
	private String email;
	private String address;
	private String nationality;
	private String grade;

	private String postcode;
	private String detailAddress;
	
}

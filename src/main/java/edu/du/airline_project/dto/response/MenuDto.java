package edu.du.airline_project.dto.response;

import lombok.Data;

@Data
public class MenuDto {

	private Integer mainId;
	private String mainMenu;
	private Integer subId;
	private String subMenu;
	private String type;
	private String mapping;
	
}

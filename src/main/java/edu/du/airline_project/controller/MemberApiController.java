package edu.du.airline_project.controller;

import edu.du.airline_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

	@Autowired
	private UserService userService;



}

package edu.du.airline_project.controller;

import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.EmailService;
import edu.du.airline_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@GetMapping("/sendNewPw")
	public String sendNewPw(@RequestParam("email") String email) {
		String code = null;
		try {
			code = emailService.sendPwCodeMessage(email);
			System.out.println("인증코드 : " + code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return code;
	}

	/*
	 * 머지할때 오류로 수정 해야함 -정다운
	 * 
	 * @GetMapping("/searchId") public int sendNewPw1( @RequestParam("id") String
	 * id) { int result = 1; System.out.println("id : " + id); User user =
	 * userService.readByid(id); if(user.getId() != id) { result = 0; } return
	 * result; }
	 */
	// 머지할때 오류로 수정 해야함 -정다운
	@GetMapping("/searchId")
	public int sendNewPw1(@RequestParam("id") String id) {
		int result = 1;
		System.out.println("id : " + id);
		User user = userService.readByid(id);
		if (user.getId() != id) {
			result = 0;
		}
		return result;
	}

	@GetMapping("/existsById")
	public boolean existsById(@RequestParam String id) {
		User userId = userService.readUserById(id);

		if (userId == null) {
			return true;
		} else {
			return false;
		}
	}
	
}

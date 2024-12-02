package edu.du.airline_project.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 치승
 * 에러페이지 
 */

@ControllerAdvice
@Controller
public class WebErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println("Status code: " + status); // 로그 추가

		if (status != null && Integer.parseInt(status.toString()) == HttpStatus.NOT_FOUND.value()) {
			// NOT_FOUND : 404에러
			
			return "layout/errorPage";
		} else if (status != null && Integer.parseInt(status.toString()) == HttpStatus.FORBIDDEN.value()) {
			// FORBIDDEN : 권한 거절
			
			return "layout/errorPage";
		}
		return "layout/errorPage";
	}
}

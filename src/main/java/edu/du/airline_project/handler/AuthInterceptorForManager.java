package edu.du.airline_project.handler;

import edu.du.airline_project.handler.exception.UnAuthException;
import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.utils.Define;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestControllerAdvice
public class AuthInterceptorForManager implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if (principal != null && !"관리자".equals(principal.getUserRole())) {
			throw new UnAuthException("접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
		}
		return true;
	}
	
}

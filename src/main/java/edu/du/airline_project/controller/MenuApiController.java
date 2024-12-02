package edu.du.airline_project.controller;

import edu.du.airline_project.dto.response.MenuDto;
import edu.du.airline_project.dto.response.ResponseDto;
import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.MenuService;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MenuApiController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 메인 메뉴를 공유하는 서브 메뉴 리스트 반환
	 */
	@GetMapping("/subMenuList/{name}")
	public ResponseDto<List<MenuDto>> subMenuListData(@PathVariable String name) {
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		List<MenuDto> data = null;
		
		if (user != null) {
			if ("관리자".equals(user.getUserRole())) {
				data = menuService.readBySubMenuAndType(name, "관리자");
			} else {
				data = menuService.readBySubMenuAndType(name, "회원");
			}
			
		} else {
			data = menuService.readBySubMenuAndType(name, "회원");
		}
		
		
		ResponseDto<List<MenuDto>> response 
			= new ResponseDto<List<MenuDto>>(HttpStatus.OK.value(), 
					Define.CODE_SUCCESS, "서브 메뉴 조회", Define.RESULT_CODE_SUCCESS, data);
		
		return response;
	}
	
}

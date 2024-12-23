package edu.du.airline_project.controller;

import edu.du.airline_project.repository.model.Mileage;
import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.MileageService;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MileageApiController {
	@Autowired
	private MileageService mileageService;

	@Autowired
	private HttpSession session;

	// MIME -- application/json
	// 클라언트 ---> (http message body data) 서버
	// http://localhost/mileage/selectAll
	// 파싱처리 1 2 3 4 5
	@GetMapping("/selectMileageList")
	@ResponseBody
	public List<Mileage> MileageListProc(Model model, @RequestParam Map<String, String> data) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		String startTime = data.get("startTime");
		String endTime = data.get("endTime");
		
		String isAllSearch = data.get("isAllSearch");
		String isUpSearch = data.get("isUpSearch");
		String isUseSearch = data.get("isUseSearch");
		String isExpireSearch = data.get("isExpireSearch");
		/*
		 * data.entrySet().forEach(e -> { System.out.println("e key : " + e.getKey());
		 * System.out.println("e value : " + e.getValue()); });
		 */

		String memberId = principal.getId();
		List<Mileage> savemileage = mileageService.readSaveMileageList(memberId, startTime, 
				endTime,isAllSearch,isUpSearch,isUseSearch,isExpireSearch);
		model.addAttribute("savemileage", savemileage);
		System.out.println("savemileage : " + savemileage);
		return savemileage;
	}
}

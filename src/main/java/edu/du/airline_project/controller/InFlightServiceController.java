package edu.du.airline_project.controller;

import edu.du.airline_project.dto.response.InFlightMealResponseDto;
import edu.du.airline_project.dto.response.SpecialMealResponseDto;
import edu.du.airline_project.enums.MealDetail;
import edu.du.airline_project.handler.exception.CustomRestfullException;
import edu.du.airline_project.repository.model.*;
import edu.du.airline_project.service.AirportService;
import edu.du.airline_project.service.InFlightSvService;
import edu.du.airline_project.service.RouteService;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/inFlightService")
public class InFlightServiceController {

	@Autowired
	private InFlightSvService inFlightSvService;

	@Autowired
	private AirportService airportService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private HttpSession session;

	// 기내 서비스 조회 페이지
	@GetMapping("/inFlightServiceSearch")
	public String inFlightServiceSearchPage(Model model) {

		List<InFlightService> inFlightServices = inFlightSvService.readInFlightService();
		model.addAttribute("inFlightServices", inFlightServices);

		List<Airport> regionList = airportService.readRegion();
		model.addAttribute("regionList", regionList);

		return "/in_flight/inFlightSvSearch";
	}

//	// inFlightServiceSpecialSearch?name=값
//	@GetMapping("/inFlightServiceSpecialSearch")
//	public String inFlightServiceSearch2(@RequestParam String name) {
//		return "/in_flight/inFlightServiceSpecial";
//	}

	// 특별 기내식 페이지
	@GetMapping("/inFlightServiceSpecial")
	public String inFlightServiceSpecialPage(Model model,
			@RequestParam(name = "name", defaultValue = "유아식 및 아동식", required = false) String name) {

		List<InFlightMealResponseDto> inFlightMeals = inFlightSvService.readInFlightMealByName(name);
		model.addAttribute("inFlightMeals", inFlightMeals);

		List<InFlightMeal> flightMeals = inFlightSvService.readInFlightMealCategory();
		model.addAttribute("flightMeals", flightMeals);

		User principal = (User) session.getAttribute(Define.PRINCIPAL);

		if (principal == null) {
			model.addAttribute("inFlightServiceResponseDtos", null);
		} else {
			List<InFlightMealResponseDto> inFlightServiceResponseDtos = inFlightSvService
					.readInFlightMealSchedule(principal.getId());
			model.addAttribute("inFlightServiceResponseDtos", inFlightServiceResponseDtos);
		}

		return "/in_flight/inFlightServiceSpecial";
	}

	// 기내 서비스 페이지
	@GetMapping("/list")
	public String inFlightServiceListPage(Model model) {
		List<InFlightService> inFlightServices = inFlightSvService.readInFlightService();
		model.addAttribute("inFlightServices", inFlightServices);

		return "/in_flight/inFlightService";
	}

	// 특별 기내식 신청 내역 페이지
	@GetMapping("/myServiceReq")
	public String myServiceReqPage(Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		List<SpecialMealResponseDto> specialMealResponseDtos = inFlightSvService
				.readRequestMealByMemberId(principal.getId());
		List<InFlightMealResponseDto> inFlightServiceResponseDtos = inFlightSvService
				.readInFlightMealSchedule(principal.getId());

		model.addAttribute("specialMealResponseDtos", specialMealResponseDtos);
		model.addAttribute("inFlightServiceResponseDtos", inFlightServiceResponseDtos);

		return "/in_flight/myServiceReq";
	}

	// 특별 기내식 신청 내역 페이지 (수정 및 삭제)
	@PostMapping("/myReqServiceDelete")
	public String myReqServiceDeleteProc(@RequestParam Integer id) {
		inFlightSvService.deleteRequestMealById(id);
		return "redirect:/inFlightService/myServiceReq";
	}

	// 특별 기내식 신청 페이지
	@GetMapping("/inFlightSpecialReq")
	public String inFlightSpecialReqPage(Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		List<SpecialMealResponseDto> specialMealResponseDtos = inFlightSvService
				.readRequestMealByMemberId(principal.getId());
		
		
		List<InFlightMealResponseDto> inFlightServiceResponseDtos = inFlightSvService
				.readInFlightMealSchedule(principal.getId());
		
		if (inFlightServiceResponseDtos.isEmpty()) {
			throw new CustomRestfullException("신청 가능한 항공권 예약 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		}
		
		List<InFlightMealDetail> babyMeal = inFlightSvService.readInFlightMealByMealId(MealDetail.BABYMEAL);
		List<InFlightMealDetail> veganMeal = inFlightSvService.readInFlightMealByMealId(MealDetail.VEGANMEAL);
		List<InFlightMealDetail> religionMeal = inFlightSvService.readInFlightMealByMealId(MealDetail.RELIGIONMEAL);
		List<InFlightMealDetail> etcMeal = inFlightSvService.readInFlightMealByMealId(MealDetail.ETCMEAL);
		List<InFlightMealDetail> lowfatMeal = inFlightSvService.readInFlightMealByMealId(MealDetail.LOWFATMEAL);

		model.addAttribute("babyMeal", babyMeal);
		model.addAttribute("veganMeal", veganMeal);
		model.addAttribute("religionMeal", religionMeal);
		model.addAttribute("etcMeal", etcMeal);
		model.addAttribute("lowfatMeal", lowfatMeal);
		
		model.addAttribute("specialMealResponseDtos", specialMealResponseDtos);
		model.addAttribute("inFlightServiceResponseDtos", inFlightServiceResponseDtos);

		return "/in_flight/inFlightSvSpecialRequest";
	}
	
}

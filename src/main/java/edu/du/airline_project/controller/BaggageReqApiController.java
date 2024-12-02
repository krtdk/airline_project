package edu.du.airline_project.controller;

import edu.du.airline_project.dto.response.BaggageReqResponseDto;
import edu.du.airline_project.dto.response.InFlightMealResponseDto;
import edu.du.airline_project.dto.response.ResponseDto;
import edu.du.airline_project.repository.model.BaggageRequest;
import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.BaggageRequestService;
import edu.du.airline_project.service.InFlightSvService;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class BaggageReqApiController {

	@Autowired
	private BaggageRequestService baggageRequestService;
	@Autowired
	private InFlightSvService inFlightSvService;
	@Autowired
	private HttpSession session;

	@GetMapping("/baggageReq")
	public List<BaggageReqResponseDto> baggageReqPage() {
		/*
		 * baggageRequestService.createBaggageReq(memberId, departureDate,
		 * baggageReqRequest);
		 */
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		List<BaggageReqResponseDto> baggageRequests = baggageRequestService.readBaggageReqByMemberId(principal.getId());

		return baggageRequests;
	}

	// service단에서 퍼스트는 *3 해야 하고 비지니스, 이코노미는 *2 해야 함.
	// 좌석 수에 따른 수하물 개수를 동적으로 갖고 오기 위해 사용
	@GetMapping("/maxCount")
	public InFlightMealResponseDto maxCount(@RequestParam String ticketId) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		// 좌석 수를 동적으로 갖고 오려고
		InFlightMealResponseDto flightMealResponseDto = inFlightSvService
				.readInFlightRequestForSeatCount(principal.getId(), ticketId);
		return flightMealResponseDto;
	}

	// MIME / application/json
	@PostMapping("/checkedBaggageProc")
	public ResponseDto<?> checkedBaggageProc(@RequestBody BaggageRequest baggageRequest) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		baggageRequest.setMemberId(principal.getId());
		System.out.println(baggageRequest);
		int result = baggageRequestService.createBaggageReq(baggageRequest);
		System.out.println(result);
		if(result != 1) {
			ResponseDto<Object> failMsg = ResponseDto.builder()
					.statusCode(HttpStatus.BAD_REQUEST.value())
					.message("신청 실패")
					.build();
			System.out.println(failMsg);
			return failMsg;
		} else {
			ResponseDto<Object> successMsg = ResponseDto
					.builder()
					.statusCode(HttpStatus.OK.value())
					.message("신청 완료되었습니다.").build();
			System.out.println(successMsg);
			return successMsg;
		}
	}

}

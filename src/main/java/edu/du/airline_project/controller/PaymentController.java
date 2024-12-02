package edu.du.airline_project.controller;

import edu.du.airline_project.dto.kakao.PayApproveDto;
import edu.du.airline_project.dto.kakao.PaymentResponseDto;
import edu.du.airline_project.dto.kakao.RefundResponseDto;
import edu.du.airline_project.dto.request.RefundDto;
import edu.du.airline_project.dto.response.MemberInfoDto;
import edu.du.airline_project.dto.response.ResponseDto;
import edu.du.airline_project.dto.response.TicketAllInfoDto;
import edu.du.airline_project.dto.response.TicketDto;
import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.*;
import edu.du.airline_project.utils.Define;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private HttpSession session;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private CoolSmsService coolSmsService;

	@Autowired
	private UserService userService;

	@Autowired
	private RefundService refundService;

	private final String KAKAO_API_KEY = "DEV1F174BE64A60D67FE9CAA42B8310C7C22CA6B";

	@Autowired
	private MileageService mileageService;

	/**
	 * @return 카카오페이 결제 요청 페이지
	 */
	@PostMapping("/request")
	@ResponseBody
	public String kakaoPayPage(@RequestBody TicketDto ticketDto) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + KAKAO_API_KEY);
		headers.add("Content-Type", "application/json"); // Content-Type을 application/json으로 설정

		String seatNamesStr = String.join(",", ticketDto.getSeatNames());
		if (ticketDto.getSeatNames2() != null) {
			String seatNames2Str = String.join(",", ticketDto.getSeatNames2());
			ticketDto.setSeatNames2(Arrays.asList(seatNames2Str.split(",")));
		}
		ticketDto.setSeatNames(Arrays.asList(seatNamesStr.split(",")));

		// JSON 형식으로 요청 본문 생성
		JSONObject params = new JSONObject();
		params.put("cid", "TC0ONETIME");
		params.put("partner_order_id", "partner_order_id");
		params.put("partner_user_id", "partner_user_id");
		params.put("item_name", "항공권 예매");
		params.put("quantity", ticketDto.getQuantity());
		params.put("total_amount", ticketDto.getTotalAmount());
		params.put("tax_free_amount", "0");
		params.put("approval_url", "http://localhost:8082/payment/success");
		params.put("cancel_url", "http://localhost:8082/payment/cancel");
		params.put("fail_url", "http://localhost:8082/payment/fail");

		HttpEntity<String> reqEntity = new HttpEntity<>(params.toJSONString(), headers);

		ResponseEntity<PaymentResponseDto> responseDto = restTemplate.exchange
				("https://open-api.kakaopay.com/online/v1/payment/ready", HttpMethod.POST,
				reqEntity, PaymentResponseDto.class);

		// 이후 처리
		ticketDto.setTid(responseDto.getBody().getTid());
		String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();

		try {
			ticketService.createTicketAndPayment(ticketDto, userId, 0);
		} catch (Exception e) {
			ticketService.deleteTicketByPaymentCancel(userId);
			ticketService.createTicketAndPayment(ticketDto, userId, 0);
		}

		return responseDto.getBody().getNextRedirectPcUrl().toString();
	}

	/**
	 * 결제 완료 시 티켓 예약 처리
	 * @return 결제 완료 페이지
	 */
	@GetMapping("/success")
	public String reserveTicketPage(@RequestParam String pg_token, Model model) {
		System.out.println("로그인 여부: " + (User) session.getAttribute(Define.PRINCIPAL));

		String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();
		MemberInfoDto member = userService.readMemberById(userId);

		String tel = member.getPhoneNumber();
		String name = member.getKorName();

		// 결제 완료 처리 후 결제 정보 반환
		List<TicketAllInfoDto> ticketList = ticketService.updatePaymentStatusIsSuccess(userId);

		// 결제 승인 처리하기
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + KAKAO_API_KEY);
		headers.add("Content-Type", "application/json"); // Content-Type을 application/json으로 변경

		// JSON 형식으로 요청 본문 생성
		JSONObject params = new JSONObject();
		params.put("cid", "TC0ONETIME");
		params.put("tid", ticketList.get(0).getTid());
		params.put("partner_order_id", "partner_order_id");
		params.put("partner_user_id", "partner_user_id");
		params.put("pg_token", pg_token);

		HttpEntity<String> reqEntity = new HttpEntity<>(params.toJSONString(), headers);

		ResponseEntity<PayApproveDto> responseDto = restTemplate.exchange(
				"https://open-api.kakaopay.com/online/v1/payment/approve", HttpMethod.POST,
				reqEntity, PayApproveDto.class
		);

		model.addAttribute("ticketList", ticketList);

		return "/ticket/paymentSuccess";
	}

	/**
	 * 결제 취소 시
	 * @return 항공스케줄 선택 페이지
	 */
	@GetMapping("/cancel")
	public String cancelPage() {
		String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();
		// 예약 내역 삭제
		ticketService.deleteTicketByPaymentCancel(userId);

		return "redirect:/ticket/selectSchedule";
	}

	/**
	 * 결제 실패 시
	 * @return 항공스케줄 선택 페이지
	 */
	@GetMapping("/fail")
	public String failPage() {
		String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();
		// 예약 내역 삭제
		ticketService.deleteTicketByPaymentCancel(userId);

		return "redirect:/ticket/selectSchedule";
	}

	/**
	 * 환불 요청
	 */
	@PostMapping("/refund")
	public String refundProc(@RequestBody RefundDto refundDto) { // @RequestBody 추가
		String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();

		// 취소 금액 계산
		Long paymentAmount = refundDto.getPaymentAmount();
		Integer scheduleType = refundDto.getScheduleType();
		Integer dayCount = refundDto.getDayCount();
		Integer adultCount = refundDto.getAdultCount();
		Integer childCount = refundDto.getChildCount();

		// 카카오페이라면
		if (refundDto.getTid().substring(0, 1).equals("T")) {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "SECRET_KEY " + KAKAO_API_KEY);
			headers.add("Content-Type", "application/json"); // Content-Type을 application/json으로 변경

			// 성인 1인 기준 환불 수수료
			Long fee = refundService.readRefundFee(scheduleType, dayCount);
			Long totalFee = (fee * adultCount) + Math.round(fee * childCount * Define.CHILD_PRICE_RATE);
			Long refundAmount = paymentAmount - totalFee;

			// JSON 형식으로 요청 본문 생성
			JSONObject params = new JSONObject();
			params.put("cid", "TC0ONETIME");
			params.put("tid", refundDto.getTid());
			params.put("cancel_amount", refundAmount);
			params.put("cancel_tax_free_amount", "0");

			HttpEntity<String> reqEntity = new HttpEntity<>(params.toJSONString(), headers);

			ResponseEntity<RefundResponseDto> responseDto = restTemplate.exchange(
					"https://open-api.kakaopay.com/online/v1/payment/cancel", HttpMethod.POST,
					reqEntity, RefundResponseDto.class
			);
		} else {
			// 마일리지 결제라면
			Long fee = (long) Math.floor(refundService.readRefundFee(scheduleType, dayCount) * Define.MILES_TICKET_RATE);
			Long totalFee = (fee * adultCount) + Math.round(fee * childCount * Define.CHILD_PRICE_RATE);
			Long refundAmount = paymentAmount - totalFee;

			// 마일리지 환불 처리
			mileageService.updateUseMilesDataStatus(userId, totalFee, refundAmount, refundDto.getTicketId());
		}

		// 환불 관련 DB 처리
		ticketService.updateStatusRefund(refundDto.getTid(), refundDto.getTicketId(), refundDto.getTicketType());

		return "redirect:/ticket/detail/" + refundDto.getTicketId();
	}

/**
 \	 * 마일리지 결제
 /
 / @PostMapping("/miles")
 @ResponseBody
 public ResponseDto<String> milesPaymentProc(@RequestBody TicketDto ticketDto) {


 int statusCode = HttpStatus.OK.value();
 int code = Define.CODE_SUCCESS;
 String message = "결제가 성공했습니다.";
 String resultCode = Define.RESULT_CODE_SUCCESS;
 String data = "";

 String userId = ((User) session.getAttribute(Define.PRINCIPAL)).getId();
 // 현재 마일리지

 Long milesPrice = ticketDto.getMilesPrice();
 // 결제할 마일리지
 if (ticketDto.getScheduleId2() != null) {
 milesPrice += ticketDto.getMilesPrice2();
 }

 // 현재 마일리지보다 결제할 마일리지가 높다면
 if (currentMiles < milesPrice) {
 statusCode = HttpStatus.BAD_REQUEST.value();
 code = Define.CODE_FAIL;
 message = "마일리지가 부족합니다.";
 resultCode = Define.RESULT_CODE_FAIL;

 } else {
 // 결제 처리 (티켓아이디 반환)
 try {
 data = ticketService.createTicketAndPayment(ticketDto, userId, 1);

 } catch (Exception e) {
 // 뒤로가기 했다가 다시 결제 요청을 할 경우를 대비해서 삭제했다가 다시 추가
 ticketService.deleteTicketByPaymentCancel(userId);
 data = ticketService.createTicketAndPayment(ticketDto, userId, 1);
 }

 }
 return new ResponseDto<String>(statusCode, code, message, resultCode, data);
 } */
}
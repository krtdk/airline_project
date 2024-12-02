package edu.du.airline_project.controller;

import edu.du.airline_project.dto.GifticonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GifticonApiController {

	@Autowired
	private HttpSession session;
	// GifticonService를 제거하거나 필요한 기능만 남깁니다.
	// @Autowired
	// private GifticonService gifticonService;

	@GetMapping("/api/gifticonList")
	public List<GifticonDto> gifticonList(Model model, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String radio) {
		// GifticonService와 연관된 코드 삭제
		// User principal = (User) session.getAttribute(Define.PRINCIPAL);
		// String memberId = principal.getId();
		// List<GifticonDto> gifticonDtos = gifticonService.readGifticonListById(memberId,startTime,endTime,radio);

		// 모델에 gifticonDtos을 추가할 필요가 없다면 아래와 같이 수정
		// model.addAttribute("gifticonDtos", gifticonDtos);
		return null; // 적절한 반환값으로 수정
	}

	@GetMapping("/api/managerGifticonList")
	public List<GifticonDto> managerGifticonList(Model model, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String radio, @RequestParam String memberId) {
		// 관리자가 Gifticon 관련 데이터를 처리할 필요가 없으면 아래와 같이 수정
		return null; // 적절한 반환값으로 수정
	}
}

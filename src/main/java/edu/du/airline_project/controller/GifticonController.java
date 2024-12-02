package edu.du.airline_project.controller;

import edu.du.airline_project.repository.model.User;
import edu.du.airline_project.service.GifticonService;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/gifticon")
public class GifticonController {

	@Autowired
	private HttpSession session;
	@Autowired
	private GifticonService gifticonService;
	
	@GetMapping("/list")
	public String gifticonList() {
		
		return "/myPage/gifticonListPage";
		
	}
	
	@PostMapping("/deleteGifticonById")
	public String deleteGifticon(@RequestParam("gifticonId") String[] gifticonId,@RequestParam("productId") String[] productId,@RequestParam("amount") String[] amount,
			@RequestParam("name") String[] name,@RequestParam("brand") String[] brand, String memberId) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		memberId = principal.getId();
		for (int i = 0; i < gifticonId.length; i++) {
			gifticonService.createRevokeGifticon(gifticonId[i]);
			gifticonService.updateMileageAndGifticonStatus(memberId, gifticonId[i]);
		}
		
		
		return "redirect:/gifticon/list";
		
	}
}

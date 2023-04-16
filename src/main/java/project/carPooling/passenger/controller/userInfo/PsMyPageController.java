package project.carPooling.passenger.controller.userInfo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DReview;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerReviewRepository;

@RequiredArgsConstructor
@RequestMapping("/passenger")
@Controller
public class PsMyPageController {
	
	private final PassengerReviewRepository passengerReviewRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/passengerCarpool/myPage")
	public String myPage(HttpServletRequest req, Model model) {
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		model.addAttribute("passengerInfo", passengerInfo);
		
		List<PReview> pReviewList = passengerReviewRepository.selectMyReview(passengerInfo.getPIdx());
		List<DReview> dReviewList = passengerReviewRepository.selectDrReview(passengerInfo.getPIdx());
		model.addAttribute("myReviewList", pReviewList);
		model.addAttribute("drReviewList", dReviewList);
		return "passenger/pMyPage";
	}
}

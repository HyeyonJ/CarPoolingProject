package project.carPooling.passenger.controller.reivew;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerReviewRepository;

@RequiredArgsConstructor
@RequestMapping("/passenger")
@Controller
public class PsReviewController {
	
	private final PassengerReviewRepository passengerReviewRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/passengerCarpool/review")
	public String review(Model model, @ModelAttribute PReview pReview) {
		passengerReviewRepository.updateCompleteStatus(pReview.getRIdx());
		model.addAttribute("pReview", pReview);
		return "passenger/pReview";
	}
	
	@ResponseBody
	@PostMapping("/passengerCarpool/review")
	public void reviewForm(@ModelAttribute PReview pReview, HttpServletRequest req) {
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		pReview.setFromIdx(passengerInfo.getPIdx());
		passengerReviewRepository.insert(pReview);
	}
	
	
}	

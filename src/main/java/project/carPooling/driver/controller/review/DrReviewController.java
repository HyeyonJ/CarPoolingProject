package project.carPooling.driver.controller.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverReviewRepository;
import project.carPooling.global.session.SessionManager;

@RequiredArgsConstructor
@RequestMapping("/driver")
@Controller
public class DrReviewController {
	
	private final DriverReviewRepository driverReviewRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/driverCarpool/review")
	public String review() {
		return "driver/dReview";
	}
	
	@ResponseBody
	@PostMapping("/driverCarpool/review")
	public void reviewForm(@ModelAttribute DReview dReview, HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		dReview.setFromIdx(driverInfo.getDIdx());
		driverReviewRepository.insert(dReview);
	}
	
	
}	

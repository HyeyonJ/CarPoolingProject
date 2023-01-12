package project.carPooling.driver.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverReviewRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;

@RequiredArgsConstructor
@RequestMapping("/driver")
@Controller
public class DrUserPage {

	private final DriverReviewRepository driverReviewRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/driverCarpool/userPage")
	public String userPage(@RequestParam Integer pIdx, Model model, HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		model.addAttribute("driverInfo", driverInfo);

		List<PReview> pReviewList = driverReviewRepository.selectDrReview(driverInfo.getDIdx());
		model.addAttribute("psReviewList", pReviewList);
		
		return "driver/dMyPage";
	}
}

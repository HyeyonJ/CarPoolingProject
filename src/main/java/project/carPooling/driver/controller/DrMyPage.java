package project.carPooling.driver.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.repository.DriverReviewRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;

@RequiredArgsConstructor
@RequestMapping("/driver")
@Controller
public class DrMyPage {
	
	private final DriverReviewRepository driverReviewRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/driverCarpool/myPage")
	public String myPage(HttpServletRequest req, Model model) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		model.addAttribute("driverInfo", driverInfo);

		List<DReview> dReviewList = driverReviewRepository.selectMyReview(driverInfo.getDIdx());
		List<PReview> pReviewList = driverReviewRepository.selectPsReview(driverInfo.getDIdx());
		model.addAttribute("myReviewList", dReviewList);
		model.addAttribute("psReviewList", pReviewList);
		System.out.println(pReviewList);
		return "driver/dMyPage";
	}
}

package project.carPooling.passenger.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.repository.PassengerReviewRepository;

@RequiredArgsConstructor
@RequestMapping("/passenger")
@Controller
public class PsUserPage {
	
	private final DriverInfoRepository driverInfoRepository;
	private final PassengerReviewRepository passengerReviewRepository;
	
	@GetMapping("/passengerCarpool/userPage")
	public String userPage(@RequestParam Integer dIdx, Model model) {
		DriverInfo driverInfo = driverInfoRepository.selectByIdx(dIdx);
		model.addAttribute("userInfo", driverInfo);
		System.out.println("여기1:" + driverInfo);
		
		List<PReview> userReviewList = passengerReviewRepository.selectUserReview(dIdx);
		System.out.println("여기2:" + userReviewList);
		model.addAttribute("userReviewList", userReviewList);
		return "passenger/pUserPage";
	}
}

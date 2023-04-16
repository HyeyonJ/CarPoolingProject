package project.carPooling.driver.controller.userInfo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.repository.DriverReviewRepository;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@RequiredArgsConstructor
@RequestMapping("/driver")
@Controller
public class DrUserPageController {

	private final PassengerInfoRepository passengerInfoRepository;
	private final DriverReviewRepository driverReviewRepository;
	
	@GetMapping("/driverCarpool/userPage")
	public String userPage(@RequestParam Integer pIdx, Model model) {
		PassengerInfo passengerInfo = passengerInfoRepository.selectByIdx(pIdx);
		model.addAttribute("userInfo", passengerInfo);
		
		List<DReview> userReviewList = driverReviewRepository.selectUserReview(pIdx);
		model.addAttribute("userReviewList", userReviewList);
		return "driver/dUserPage";
	}
	
}

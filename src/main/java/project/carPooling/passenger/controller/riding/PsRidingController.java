package project.carPooling.passenger.controller.riding;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.RidingRepository;

@RequiredArgsConstructor
@Controller
@RequestMapping("/passenger")
public class PsRidingController {

	private final RidingRepository ridingRepository;
	private final SessionManager sessionManager; 
	
	@GetMapping("/passengerCarpool/ridingPage")
	public String riding(Model model, @RequestParam Integer drIdx){
		PReview pReview = new PReview();
		model.addAttribute("drIdx", drIdx);
		model.addAttribute("pReview", pReview);
		return "passenger/pRidingPage";
	}
	
	@ResponseBody
	@PostMapping("/passengerCarpool/ridingPage")
	public Map<String, Object> riding(@RequestParam Integer drIdx) {
		Map<String, Object> map = ridingRepository.selectRIdxAndDIdx(drIdx);
		DRegistration dRegistration = ridingRepository.selectDRegistrationByDrIdx(drIdx);
		map.put("dRegistration", dRegistration);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/passengerCarpool/ridingPage/payment")
	public void payment(@RequestParam Integer dIdx, @RequestParam int dFee) {
		ridingRepository.updateDPoint(dIdx, dFee);
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/drivingStatus")
	public Integer drivingStatus(HttpServletRequest req) {
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		Integer drIdx = ridingRepository.selectDrivingStatus(passengerInfo.getPIdx());
		return drIdx;
	}
	
	
}

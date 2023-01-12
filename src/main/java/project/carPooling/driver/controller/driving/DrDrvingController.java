package project.carPooling.driver.controller.driving;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.repository.DrivingRepository;

@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DrDrvingController {
	
	private final DrivingRepository drivingRepository;
	
	@GetMapping("/driverCarpool/drivingPage")
	public String driving(Model model, @RequestParam Integer drIdx) {
		DReview dReview = new DReview();
		model.addAttribute("drIdx", drIdx);
		model.addAttribute("dReview", dReview);
		return "driver/dDrivingPage";
	}
	
	@ResponseBody
	@PostMapping("/driverCarpool/drivingPage")
	public Map<String, Object> driving(@RequestParam Integer drIdx) {
		Map<String, Object> map = drivingRepository.selectRIdxAndPIdx(drIdx);
		DRegistration dRegistration = drivingRepository.selectDRegistrationByDrIdx(drIdx);
		map.put("dRegistration", dRegistration);
		return map;
	}
	
	@ResponseBody
	@PutMapping("/driverCarpool/drivingPage")
	public void updateDrivingRegistration(@RequestParam Integer drIdx) {
		drivingRepository.updateDrivingRegistration(drIdx);
	}
	
	@ResponseBody
	@GetMapping("/driverCarpool/completeStatus")
	public String completeStatus(@RequestParam Integer rIdx) {
		return drivingRepository.selectCompleteStatus(rIdx);
	}
}

package project.carPooling.passenger.controller.socialLogin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.validation.PassengerValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger/join")
public class PsgGeneralJoinController {
	
	private Map<String, DriverInfo> passengerMap;
	
	private final PassengerInfoRepository passengerInfoRepository;
	private final PassengerValidator passengerValidator;
	
//	@GetMapping("/add")
//	public String JoinAdd(Model model) {
//		DriverInfo driverInfo = new DriverInfo();
//		model.addAttribute(driverInfo);
//		
//		return "driver/join/dJoinAdd";
//	}
	
	@GetMapping("/naver/add")
	public String JoinNaverAdd(Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute(driverInfo);
		
		return "driver/join/dJoinNaverAdd";
	}
	
}

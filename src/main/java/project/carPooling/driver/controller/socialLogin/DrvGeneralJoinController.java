package project.carPooling.driver.controller.socialLogin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.validation.DriverValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/join")
public class DrvGeneralJoinController {
	
	private Map<String, DriverInfo> driverMap;
	
	private final DriverInfoRepository driverInfoRepository;
	private final DriverValidator driverValidator;
	
//	@GetMapping("/add")
//	public String JoinAdd(Model model) {
//		DriverInfo driverInfo = new DriverInfo();
//		model.addAttribute(driverInfo);
//		
//		return "driver/join/dJoinAdd";
//	}
	
	
}

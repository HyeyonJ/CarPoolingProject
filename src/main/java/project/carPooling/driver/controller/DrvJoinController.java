package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvJoinController {
	
	@GetMapping("/join")
	public String newMember(Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute(driverInfo);
		
		return "driver/join/dJoin";
	}

}

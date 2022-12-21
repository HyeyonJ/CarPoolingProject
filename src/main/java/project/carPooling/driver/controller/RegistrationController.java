package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.CarPoolRegistration;

@Controller
public class RegistrationController {
	
	@GetMapping("/driver/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
	
	@PostMapping("/driver/driverCarpool/registration")
	public String registraionForm(@ModelAttribute CarPoolRegistration carPoolRegistration) {
		System.out.println(carPoolRegistration.toString());
		return "driver/dRegistration";
	}
	
}

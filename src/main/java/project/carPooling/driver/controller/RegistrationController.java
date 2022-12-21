package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {
	
	@GetMapping("/driver/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
}

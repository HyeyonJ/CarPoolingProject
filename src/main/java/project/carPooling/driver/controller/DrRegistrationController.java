package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.repository.RegistrationRepository;

@RequiredArgsConstructor
@Controller
public class DrRegistrationController {
	
	private final RegistrationRepository registrationRepository;
	
	@GetMapping("/driver/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
	
	@PostMapping("/driver/driverCarpool/registration")
	public String registraionForm(@ModelAttribute DRegistration dRegistration) {
		System.out.println(dRegistration.toString());
		registrationRepository.insert(dRegistration);
		return "driver/dRegistration";
	}
	
}

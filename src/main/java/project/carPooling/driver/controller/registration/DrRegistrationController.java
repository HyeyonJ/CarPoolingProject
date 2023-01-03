package project.carPooling.driver.controller.registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.RegistrationRepository;
import project.carPooling.global.session.SessionManager;

@RequiredArgsConstructor
@Controller
public class DrRegistrationController {
	
	private final RegistrationRepository registrationRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/driver/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
	
	@ResponseBody
	@PostMapping("/driver/driverCarpool/registration")
	public String registraionForm(@ModelAttribute DRegistration dRegistration, HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		dRegistration.setDIdx(driverInfo.getDIdx());
		System.out.println(dRegistration.toString());
		registrationRepository.insert(dRegistration);
		return "등록성공";
	}
	
}

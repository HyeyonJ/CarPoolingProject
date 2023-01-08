package project.carPooling.driver.controller.registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.RegistrationRepository;
import project.carPooling.global.session.SessionManager;

@Controller
@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DrRegistrationController {
	
	private final RegistrationRepository registrationRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
	
	@ResponseBody
	@PostMapping("/driverCarpool/registration")
	public String registraionForm(@ModelAttribute DRegistration dRegistration, HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		dRegistration.setDIdx(driverInfo.getDIdx());
		DRegistration dr = registrationRepository.selectRegistrationByTime(dRegistration);
		String dCommute;
		if(dr == null) {
			registrationRepository.insert(dRegistration);
			return "insert";
		} else {
			dCommute = dr.getDCommute();
		}
		return dCommute;
	}
	
}

package project.carPooling.global.home;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final SessionManager sessionManager;	
	
	@GetMapping("/")
	public String carPoolingHome(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();
		if (session == null) { return "home"; }
		
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		PassengerInfo passenger = sessionManager.getPsSession(req);

		if (driverInfo == null && passenger == null ) {
			return "home";
		} else if (passenger == null ) {
			model.addAttribute("driverInfo", driverInfo);
			return "/driver/dRegistration";
		} else if (driverInfo == null ) {
			model.addAttribute("passenger", passenger);
			return "/passenger/pReservation";
		}
		return "home";
	}
}

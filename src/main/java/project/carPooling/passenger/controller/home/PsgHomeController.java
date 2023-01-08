package project.carPooling.passenger.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgHomeController {
	
	private final SessionManager sessionManager;
	
	@GetMapping("/home")
	public String passengerHome(Model model
					, HttpServletRequest req) {
		
		PassengerInfo passenger = sessionManager.getPsSession(req);
		
		if(passenger == null) { return "redirect:/passenger/login"; }
		
		model.addAttribute("passenger", passenger);
		
		return "passenger/home/pHomeMain";
	}
	
}
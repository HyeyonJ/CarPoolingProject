package project.carPooling.passenger.controller.homeController;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgHomeController {
	
	@GetMapping("/home")
	public String passengerHome(Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute(SessionVar.LOGIN_PASSENGER) == null) {
			
			return "redirect:/passenger/login"; }


		//sessionManager에서 넘어온 Object가 driver 혹은 passenger인걸 알 수 있으므로 강제 형변환해주기
		PassengerInfo passenger = (PassengerInfo)session.getAttribute(SessionVar.LOGIN_PASSENGER);
		
		if(passenger == null) { return "redirect:/passenger/login"; }
		
		model.addAttribute("passenger", passenger);
		
		return "passenger/home/pHomeMain";
	}
	
}

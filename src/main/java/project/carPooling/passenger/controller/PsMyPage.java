package project.carPooling.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@RequiredArgsConstructor
@RequestMapping("/passenger")
@Controller
public class PsMyPage {
	
	private final SessionManager sessionManager;
	
	@GetMapping("/passengerCarpool/myPage")
	public String myPage(HttpServletRequest req, Model model) {
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		model.addAttribute("passengerInfo", passengerInfo);
		return "passenger/pMyPage";
	}
}

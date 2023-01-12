package project.carPooling.passenger.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgJoinController {
	
	private final PassengerInfoRepository passengerRepository;
	
	@GetMapping("/join")
	public String newPassenger(Model model) {
		PassengerInfo passenger = new PassengerInfo();
		model.addAttribute("passenger", passenger);
		return "passenger/join/pJoinGeneral";
	}
	
	@PostMapping("/join")
	public String newPassengerInsert(@ModelAttribute PassengerInfo passenger){		
		//insert() 수행 후 로그인 페이지
		passengerRepository.insert(passenger);
		return "redirect:/passenger/login";
	}
	
	@ModelAttribute("pUserTypes")
	public PUserType[] PUserTypes() {
		return PUserType.values();
	}
	
}

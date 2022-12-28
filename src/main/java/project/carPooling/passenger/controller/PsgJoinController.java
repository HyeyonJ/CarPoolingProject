package project.carPooling.passenger.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.validation.PassengerValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger/join")
public class PsgJoinController {
	
	private final PassengerInfoRepository passengerRepository;
	private final PassengerValidator passengerValidator;
	
	@GetMapping("/general")
	public String newPassenger(Model model) {
		PassengerInfo passenger = new PassengerInfo();
		model.addAttribute("passenger", passenger);
		return "passenger/join/pJoinGeneral";
	}
	
	@PostMapping("/general")
	public String newPassengerInsert(@ModelAttribute PassengerInfo passenger
									, BindingResult bindingResult){
		passengerValidator.validate(passenger, bindingResult);
		//validator에서 passengerInfo 검증 후 bindingResult 넘겨받고
		//error 있으면, 회원가입페이지
		if (bindingResult.hasErrors()) {
			return "passenger/join/pJoinGeneral";
		}
		//error 없으면, insert() 수행 후 로그인 페이지
		passengerRepository.insert(passenger);

		return "redirect:/passenger/login";
	}
	
	@ModelAttribute("pUserTypes")
	public PUserType[] PUserTypes() {
		return PUserType.values();
	}
	
}

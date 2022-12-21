package project.carPooling.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.MybatisPassengerInfoRepository;
import project.carPooling.passenger.validation.PassengerValidator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgJoinController {
	
	private final MybatisPassengerInfoRepository mybatisPassengerRepository;
	private final PassengerValidator passengerValidator;
	
	@GetMapping("/join")
	public String newPassenger(Model model) {
		PassengerInfo passenger = new PassengerInfo();
		model.addAttribute("passenger", passenger);
		return "passenger/home/pLoginHome";
	}
	
	@PostMapping("/join")
	public String newPassengerInsert(@ModelAttribute PassengerInfo passenger
									, BindingResult bindingResult) {
		passengerValidator.validate(passenger, bindingResult);
		//validator에서 passengerInfo 검증 후 bindingResult 넘겨받고
		//error 있으면, 회원가입페이지
		if (bindingResult.hasErrors()) {
			return "passenger/join";
		}
		//error 없으면, insert() 수행 후 로그인페이지
		mybatisPassengerRepository.insert(passenger);
		return "redirect:/passenger/login";
	}
	
	
	
}

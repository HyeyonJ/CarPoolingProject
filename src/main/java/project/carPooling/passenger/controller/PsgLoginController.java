package project.carPooling.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.service.PassengerLoginService;
import project.carPooling.passenger.validation.form.PassengerLoginForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgLoginController {
	
	private final PassengerLoginService passengerLoginService;
	private final SessionManager sessionManager;
	
	@GetMapping("/login")
	public String login(Model model) {
		PassengerLoginForm pLoginForm = new PassengerLoginForm();
		model.addAttribute("pLoginForm", pLoginForm);
		
		return "passenger/login/pLogin";
	}


	//로그인하고나서 원래 이동했었던 경로로 다시 이동시키기
	@PostMapping("/login")
	public String doLogin(@ModelAttribute PassengerLoginForm pLoginForm
						, BindingResult bindingResult, HttpServletResponse resp
						, HttpServletRequest req
						, @RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
		log.info("pLoginForm {}", pLoginForm);
		
		validateLoginForm(pLoginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "passenger/login/pLogin";
		}
		
		PassengerInfo passenger = passengerLoginService.login(pLoginForm.getLoginId(), pLoginForm.getPassword());
		
		log.info("pLogin {}", passenger);
		
		if(passenger == null) {	//계정 정보가 없거나, 비밀번호가 안 맞으면 로그인 실패
			bindingResult.rejectValue("pLoginForm", "아이디 or 비밀번호 불일치");
			return "passenger/login/pLogin";
		}		
		//정상 로그인 처리가 된 경우
		//세션에 추가
		HttpSession session = req.getSession();	//getSession(true) : session이 없으면 만들고 있으면 안 만든다.
//		session.setMaxInactiveInterval(540);	//세션 유효시간
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);		
		
		//넘어온 redirectURL값이 있으면 해당 경로, 없으면 default값인 "/" 이동
		return "redirect:" + redirectURL;
	}
	
	
	@PostMapping("/passenger/logout")
	public String logout(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	
	
	public void validateLoginForm(PassengerLoginForm pLoginForm, Errors errors) {
		if(!StringUtils.hasText(pLoginForm.getLoginId())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력입니다.");
		}
		if(!StringUtils.hasText(pLoginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력입니다.");
		}
	}
	
}

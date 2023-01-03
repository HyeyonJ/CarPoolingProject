package project.carPooling.passenger.controller.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;

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
	
	private String CLIENT_ID = "80RTTYkxaQQE_nLlnxlk"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "Y28XSEjKSi"; // 애플리케이션 클라이언트 시크릿값";
	
	@GetMapping("/login")
	public String loginMain(HttpSession session, Model model) 
			throws UnsupportedEncodingException, UnknownHostException {
		PassengerLoginForm pLoginForm = new PassengerLoginForm();
		model.addAttribute("pLoginForm", pLoginForm);
		
	// 네이버		
	    String redirectURI = URLEncoder.encode("http://localhost:8080/passenger/login/naver/redirect", "UTF-8");
	    
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    System.out.println("state: " + state);
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
	        CLIENT_ID, redirectURI, state);
	    session.setAttribute("state", state);
	    model.addAttribute("apiURL", apiURL);
		
		return "passenger/login/pLoginMain";
	}

	//로그인하고나서 원래 이동했었던 경로로 다시 이동시키기
	@PostMapping("/login")
	public String doLogin(@ModelAttribute PassengerLoginForm pLoginForm
						, BindingResult bindingResult, HttpServletResponse resp
						, HttpServletRequest req
						, @RequestParam(name="redirectURL", defaultValue="/passenger/home") String redirectURL) {
		log.info("pLoginForm {}", pLoginForm);
		
		validateLoginForm(pLoginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "passenger/login/pLoginMain";
		}
		
		PassengerInfo passenger = passengerLoginService.login(pLoginForm.getLoginId(), pLoginForm.getPassword());
		
		log.info("pLogin {}", passenger);
		
		if(passenger == null) {	//계정 정보가 없거나, 비밀번호가 안 맞으면 로그인 실패
			bindingResult.rejectValue("pLoginForm", "아이디 or 비밀번호 불일치");
			return "passenger/login/pLoginMain";
		}
		//정상 로그인 처리가 된 경우
		//세션에 추가
		HttpSession session = req.getSession();	//getSession(true) : session이 없으면 만들고 있으면 안 만든다.
//		session.setMaxInactiveInterval(540);	//세션 유효시간
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);
		session.setAttribute(SessionVar.LOGIN_ID, "p"+passenger.getPIdx());
		
		//넘어온 redirectURL값이 있으면 해당 경로, 없으면 default값인 "/" 이동
		return "redirect:" + redirectURL;
	}
	
	
	@GetMapping("/logout")
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

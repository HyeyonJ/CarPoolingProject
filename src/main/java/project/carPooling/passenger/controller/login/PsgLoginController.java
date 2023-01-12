package project.carPooling.passenger.controller.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.PassengerLoginForm;
import project.carPooling.passenger.service.PassengerLoginService;

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
						, HttpServletResponse resp, HttpServletRequest req
						, @RequestParam(name="redirectURL", defaultValue="/passenger/passengerCarpool/reservation") String redirectURL
						, RedirectAttributes rAttr) {
		
		PassengerInfo passenger = passengerLoginService.login(pLoginForm.getLoginId(), pLoginForm.getPassword());

		//계정 정보가 없거나, 로그인 정보 불일치
		if(passenger == null) {
			rAttr.addFlashAttribute("login", false);
			return "redirect:/passenger/login";
		}
		//계정 정보가 있으나, 탈퇴한 회원
		if(passenger.getPSignOut() == true) {
			rAttr.addFlashAttribute("signOut", true);
			return "redirect:/passenger/login";
		}		
		
		//정상 로그인 처리가 된 경우 세션에 추가
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);
		session.setAttribute(SessionVar.LOGIN_ID, passenger.getPIdx());
		session.setAttribute(SessionVar.LOGIN_NAME, passenger.getPUserName());
		
		//넘어온 redirectURL값이 있으면 해당 경로로 이동
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
	
}

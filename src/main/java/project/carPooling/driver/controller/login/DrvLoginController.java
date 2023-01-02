package project.carPooling.driver.controller.login;

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
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.service.DriverLoginService;
import project.carPooling.driver.validation.form.DriverLoginForm;
import project.carPooling.global.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvLoginController {

	private final DriverLoginService driverLoginService;
	
	private String CLIENT_ID = "80RTTYkxaQQE_nLlnxlk"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "Y28XvSEjKSi"; // 애플리케이션 클라이언트 시크릿값";
	
	@GetMapping("/login")
	public String loginMain(HttpSession session, Model model)
				throws UnsupportedEncodingException, UnknownHostException {

////////////////////// dirverLoginForm 이거 제대로 확인///////////////////////////////////
		DriverLoginForm dLoginForm = new DriverLoginForm();
		model.addAttribute("dLoginForm", dLoginForm);
		
	// 네이버		
	    String redirectURI = URLEncoder.encode("http://localhost:8080/driver/login/naver/redirect", "UTF-8");
	    
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    System.out.println("state: " + state);
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
	        CLIENT_ID, redirectURI, state);
	    session.setAttribute("state", state);
	    model.addAttribute("apiURL", apiURL);
		
	    return "driver/login/dLoginMain";
	}


	//로그인하고나서 원래 이동했었던 경로로 다시 이동시키기
	@PostMapping("/login")
	public String doLogin(@ModelAttribute DriverLoginForm dLoginForm
						, BindingResult bindingResult, HttpServletResponse resp
						, HttpServletRequest req
						, @RequestParam(name="redirectURL", defaultValue="/driver/home") String redirectURL) {
		log.info("dLoginForm {}", dLoginForm);
		
		validateLoginForm(dLoginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "driver/login/dLoginMain";
		}
		
		DriverInfo driver = driverLoginService.login(dLoginForm.getLoginId(), dLoginForm.getPassword());
		
		log.info("dLogin {}", driver);
		
		if(driver == null) {	//계정 정보가 없거나, 비밀번호가 안 맞으면 로그인 실패
			bindingResult.rejectValue("dLoginForm", "아이디 or 비밀번호 불일치");
			return "driver/login/dLoginMain";
		}
		//정상 로그인 처리가 된 경우
		//세션에 추가
		HttpSession session = req.getSession();	//getSession(true) : session이 없으면 만들고 있으면 안 만든다.
//		session.setMaxInactiveInterval(540);	//세션 유효시간
		session.setAttribute(SessionVar.LOGIN_DRIVER, driver);		
		
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
	
	public void validateLoginForm(DriverLoginForm dLoginForm, Errors errors) {
		if(!StringUtils.hasText(dLoginForm.getLoginId())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력입니다.");
		}
		if(!StringUtils.hasText(dLoginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력입니다.");
		}
	}
	
}

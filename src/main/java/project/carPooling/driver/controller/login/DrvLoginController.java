package project.carPooling.driver.controller.login;

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
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.domain.DriverLoginForm;
import project.carPooling.driver.service.DriverLoginService;
import project.carPooling.global.session.SessionVar;

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

		DriverLoginForm dLoginForm = new DriverLoginForm();
		model.addAttribute("dLoginForm", dLoginForm);
		
	// 네이버		
	    String redirectURI = URLEncoder.encode("http://localhost:8080/driver/login/naver/redirect", "UTF-8");
	    
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    System.out.println("dLoginMain-naver-state: " + state);
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
						, HttpServletResponse resp, HttpServletRequest req
						, @RequestParam(name="redirectURL", defaultValue="/driver/driverCarpool/registration") String redirectURL
						, RedirectAttributes rAttr) {
		
		DriverInfo driver = driverLoginService.login(dLoginForm.getLoginId(), dLoginForm.getPassword());
		
		//계정 정보가 없거나, 로그인 정보 불일치
		if(driver == null) {
			rAttr.addFlashAttribute("login", false);
			return "redirect:/driver/login";
		}
		//계정 정보가 있으나, 탈퇴한 회원
		if(driver.getDSignOut() == true) {
			rAttr.addFlashAttribute("signOut", true);
			return "redirect:/driver/login";
		}
		
		//정상 로그인 처리가 된 경우 세션에 추가
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driver);	
		session.setAttribute(SessionVar.LOGIN_ID, driver.getDIdx());
		session.setAttribute(SessionVar.LOGIN_NAME, driver.getDUserName());
		
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

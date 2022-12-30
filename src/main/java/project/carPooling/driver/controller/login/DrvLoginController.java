package project.carPooling.driver.controller.login;

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
	
	@GetMapping("/login")
	public String login(Model model) {
		DriverLoginForm dLoginForm = new DriverLoginForm();
		model.addAttribute("dLoginForm", dLoginForm);
		
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

package project.carPooling.passenger.controller.socialLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.service.PsgKakaoService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger/login")
public class PsgKaKaoLoginController {
	
	@Autowired
	private final PsgKakaoService psgKakaoService;	
	private final PassengerInfoRepository passengerInfoRepository;
//	 private KakaoLogin kakao_restapi = new KakaoLogin();
	
// kakao 로그인 - 토큰을 이용해서 리다이렉트 후 로그인 처리
    @GetMapping("/kakao/redirect")
    public String loginKakao(
    		Model model,
//    		 BindingResult bindingResult, 
    		@RequestParam(value = "code", required = false) String code,
    		HttpServletResponse resp, HttpServletRequest req,
    		@RequestParam(name="redirectURL", defaultValue="/passenger/passengerCarpool/reservation") String redirectURL
    		) throws Exception {
		
    	System.out.println("code=" + code);
		
		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = psgKakaoService.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);
		
		PassengerInfo passengerKakao = psgKakaoService.getKaKaoUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("------------------------------");
		
		model.addAttribute("passengerKakao", passengerKakao);
		
		PassengerInfo passenger = passengerInfoRepository.selectByEmail(passengerKakao.getPUserEmail());
		
		if ( passenger == null ) {
			return "passenger/join/pKakaoCallback";
			
		}
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);
//		session.setMaxInactiveInterval(540);
		
		return "redirect:" + redirectURL;
    }
    
    

// kakao 추가 정보가 입력이 안 되어 있을 시 등록하는 양식 보여준 후 받아서 처리
	@PostMapping("/kakao/join")
	public String KakaoInsert(@ModelAttribute PassengerInfo passenger
							, HttpServletRequest req, BindingResult bindingResult) {
		System.out.println("passenger : " + passenger);
		System.out.println("---------------------------");
		
//		memberValidator.validate(member, bindingResult);
		
//		if(bindingResult.hasErrors()) {
//			return "members/newMember";
//		}
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);
		passenger.setPUserType(null);
		
//		passengerInfoRepository.insert(passenger);
		return "passenger/pReservation";
	}
	

	
// 카카오 세션
	
	
// 카카오 cookie	
//	@PostMapping("/kakao/login_cookie")
//	public String KakaoCookie(@ModelAttribute LoginForm loginForm, 
//									BindingResult bindingResult,
////			 						HttpSession session,
//									HttpServletResponse resp) {
//
//		log.info("loginForm {}", loginForm);
//
//		validateLoginForm(loginForm, bindingResult);
//
//		if (bindingResult.hasErrors()) {
//			return "login/login";
//		}
//
//		Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
//
//		log.info("login {}", member);
//
//		if (member == null) {
//// 계정정보가 없거나 비밀번호가 안맞거나 로그인 실패
//			bindingResult.reject("loginForm", "아이디 or 비밀번호");
//			return "login/login";
//		}
//
//
//// 쿠키를 추가
//		Cookie cookie = new Cookie("loginId", member.getLoginId());
//		Cookie cookie2 = new Cookie("memberId", member.getId().toString());
//		resp.addCookie(cookie);
//		resp.addCookie(cookie2);
//
//// session.setAttribute("name", member.getName());
//
//		return "redirect:/";
//	}
	
	
//	public void validateLoginForm(LoginForm loginForm, Errors errors) {
//
//		if (!StringUtils.hasText(loginForm.getDUserEmail())) {
//			errors.rejectValue("dUserEmail", null, "이메일은 필수 입력입니다.");
//		}
//
//		if (!StringUtils.hasText(loginForm.getDUserPw())) {
//			errors.rejectValue("dUserPw", null, "비밀번호 필수 입력입니다.");
//		}
//	}
	
	
// 로그아웃
//		@PostMapping("/logout_cookie")
//		public String kakao_logout_cookie(HttpServletResponse resp) {
//			Cookie cookie = new Cookie("memberId", null);
//	// 쿠키 죽이기
//			cookie.setMaxAge(0);
//			resp.addCookie(cookie);
//
//			return "redirect:/";
//		}

		@PostMapping("/logout_session")
		public String kakao_logout_session(HttpServletRequest req) {

			return "redirect:/";
		}

		@PostMapping("/logout")
		public String kakao_logout(HttpServletRequest req) {
			HttpSession session = req.getSession(false);

			if (session != null) {
				session.invalidate();
			}
			return "redirect:/";
		}
    

}

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PUserType;
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
    		@RequestParam(name="redirectURL", defaultValue="/passenger/passengerCarpool/reservation") String redirectURL,
    		RedirectAttributes rAttr) throws Exception {
		
    	System.out.println("code=" + code);
		
		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = psgKakaoService.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);
		
		PassengerInfo passengerKakao = psgKakaoService.getPsgKaKaoUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("------------------------------");
		
		model.addAttribute("passenger", passengerKakao);
		
		PassengerInfo passenger = passengerInfoRepository.selectByEmail(passengerKakao.getPUserEmail());
		
		if(passenger != null && passenger.getPSignOut() == true) {
			rAttr.addFlashAttribute("signOut", true);
			return "redirect:/passenger/login";
		}
		
		if ( passenger == null ) {
			return "passenger/join/pKakaoCallback";
			
		}
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passenger);
		
		return "redirect:" + redirectURL;
    }
    
 

// kakao 추가 정보가 입력이 안 되어 있을 시 등록하는 양식 보여준 후 받아서 처리
	@PostMapping("/kakao/join")
	public String KakaoInsert(@ModelAttribute PassengerInfo joinData
							, HttpServletRequest req) {
		
		passengerInfoRepository.insert(joinData);
		PassengerInfo passengerInfo = passengerInfoRepository.selectByEmail(joinData.getPUserEmail());
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo);
		
		return "redirect:/passenger/passengerCarpool/reservation";
	}

	@ModelAttribute("pUserTypes")
	public PUserType[] PUserTypes() {
		return PUserType.values();
	}
	

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

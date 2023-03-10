package project.carPooling.driver.controller.socialLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.service.DrvKakaoService;
import project.carPooling.global.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/login")
public class DrvKaKaoLoginController {
	
	@Autowired
	private final DrvKakaoService driverKakaoService;
	private final DriverInfoRepository driverInfoRepository;
//	 private KakaoLogin kakao_restapi = new KakaoLogin();	

    
// kakao 로그인 - 토큰을 이용해서 리다이렉트 후 로그인 처리
// kakao 회원 가입 
    @GetMapping("/kakao/redirect")
    public String loginKakao(
    		Model model,
//    		 BindingResult bindingResult, 
    		@RequestParam(value = "code", required = false) String code,
    		HttpServletResponse resp, HttpServletRequest req,
    		@RequestParam(name="redirectURL", defaultValue="/driver/driverCarpool/registration") String redirectURL,
    		RedirectAttributes rAttr
    		) throws Exception {
		
    	System.out.println("controller/code=" + code);
		
		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = driverKakaoService.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);
		
		DriverInfo driverInfo = driverKakaoService.getKaKaoUserInfo(access_Token);
//		DriverInfo userInfo = dks.getKaKaoUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("------------------------------");
//		System.out.println("userinfo" + userInfo);
		System.out.println("카카오 로그인 완료");
		System.out.println("------------------------------");
		
		
		model.addAttribute("driverInfo", driverInfo);
		
		DriverInfo driverInfo2 = driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
		
		if(driverInfo2 != null && driverInfo2.getDSignOut() == true) {
			rAttr.addFlashAttribute("signOut", true);
			return "redirect:/driver/login";
		}
		
		if (driverInfo2 == null ) {
			return "driver/join/dKakaoCallback";
			
		}
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo2);
		session.setAttribute(SessionVar.LOGIN_ID, driverInfo2.getDIdx());
		session.setAttribute(SessionVar.LOGIN_NAME, driverInfo2.getDUserName());
//		session.setMaxInactiveInterval(540);
		
		return "redirect:" + redirectURL;
    }
    
	// kakao 추가 정보가 입력이 안 되어 있을 시 등록하는 양식 보여준 후 받아서 처리
	@PostMapping("/kakao/join")
	public String KakaoInsert(@ModelAttribute DriverInfo joinData, HttpServletRequest req, BindingResult bindingResult) {

		driverInfoRepository.insert(joinData);
		DriverInfo driverInfo = driverInfoRepository.selectByEmail(joinData.getDUserEmail());
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
		return "redirect:/driver/driverCarpool/registration";
	}
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
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

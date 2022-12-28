package project.carPooling.driver.controller.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.service.DrvKakaoService;
import project.carPooling.driver.validation.DriverValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/login")
public class DrvKaKaoLoginController {
	
//	 private KakaoLogin kakao_restapi = new KakaoLogin();
	DrvKakaoService drvKakaoService = new DrvKakaoService();
	
	private final DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private DrvKakaoService dks;

//    @GetMapping("/kakao")
//    public String loginPage() {
//        return "driver/login/dLoginMain";
//    }
    
    @GetMapping("/kakao/redirect")
    public String loginKakao(@RequestParam(value = "code", required = false) String code, Model model) throws Exception {
		System.out.println("code=" + code);
		
		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = dks.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);
		
		DriverInfo driverInfo = drvKakaoService.getKaKaoUserInfo(access_Token);
		DriverInfo userInfo = dks.getKaKaoUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("------------------------------");
		System.out.println("userinfo" + userInfo);
		System.out.println("------------------------------");
		model.addAttribute("driverInfo", driverInfo);
		model.addAttribute("userinfo", userInfo);
		model.addAttribute("code",code);
		
		DriverInfo driverInfo2 =driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
//		System.out.println("###nickname#### : " + userInfo.get("nickname"));
//		System.out.println("###email#### : " + userInfo.get("email"));
		if ( driverInfo2 != null ) {
			
			return "driver/dRegistration";
		} else {
    	return "driver/login/dKakaoCallback";

		}
	
    }
    
	@PostMapping("/kakao/registration")
	public String KakaoInsert(@ModelAttribute DriverInfo driverInfo, BindingResult bindingResult) {
		System.out.println("driverInfo : " + driverInfo);
		System.out.println("---------------------------");
		
//		memberValidator.validate(member, bindingResult);
		
//		if(bindingResult.hasErrors()) {
//			return "members/newMember";
//		}
		
		driverInfoRepository.insert(driverInfo);
		return "driver/dRegistration";
	}
    

}

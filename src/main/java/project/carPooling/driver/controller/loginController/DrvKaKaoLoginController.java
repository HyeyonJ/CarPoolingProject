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
//		System.out.println("###nickname#### : " + userInfo.get("nickname"));
//		System.out.println("###email#### : " + userInfo.get("email"));
		
    	return "driver/login/dKakaoCallback";
    }
    
	@PostMapping("/kakao/registration")
	public String KakaoInsert(@ModelAttribute DriverInfo driverInfo, BindingResult bindingResult) {
		log.info("driverInfo = {}", driverInfo);
		System.out.println("driverInfo : " + driverInfo);
		System.out.println("어디서 오류가 납니까??");
		
//		memberValidator.validate(member, bindingResult);
		
//		if(bindingResult.hasErrors()) {
//			return "members/newMember";
//		}
		
		driverInfoRepository.insert(driverInfo);
		return "driver/dRegistration";
	}
    
    

    // 업데이트 처리
//    @GetMapping("/update/{d_email}")
//	public String updateKakao(Model model, @PathVariable("d_email") int dEmail) {
//		DriverInfo driverInfo = foodRepository.selectById(dEmail);
//		model.addAttribute("food",foodItem);
//		
//		return "foods/update.html";
//	}
//	
//	@PostMapping("/update/{d_email}")
//	public String updateKakaoProcess(Model model
//			, @PathVariable("d_email") int dEmail
//			, @ModelAttribute DriverInfo driverInfo) {
//		log.info(driverInfo.toString());	// 넘어온거 보기
////		log.info("/update/{}", foodId);
//		foodRepository.update(foodId, foodItem);
		
		// 여기서 바로 foods/id에 해당하는 foods/food 페이지를 보여주면서 <- food 객체를 주입
		// 1번.
//		model.addAttribute("food",foodItem);
//		return "foods/update.html";
//		// 2번.
//		// food 상세정보를 보여주는 경로가 이미 존재. -> 이미 존재하는 메소드를 활용
//		return "redirect:/foods/{foodId}";
//	}
}

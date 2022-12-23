package project.carPooling.driver.controller.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/driver/login")
public class DrvKaKaoLoginController {
	
//	 private KakaoLogin kakao_restapi = new KakaoLogin();

    @GetMapping("/kakao")
    public String loginPage() {
        return "driver/login/dLoginMain";
    }
    
//    @GetMapping("/kakao")
//    public String getCI(@RequestParam String code, Model model) throws IOException {
//        System.out.println("code = " + code);
//        String access_token = ks.getToken(code); 
//        Map<String, Object> userInfo = ks.getUserInfo(access_token);
//        model.addAttribute("code", code);
//        model.addAttribute("access_token", access_token);
//        model.addAttribute("userInfo", userInfo);
//
//        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
//        return "index";
//    }
    
    

}

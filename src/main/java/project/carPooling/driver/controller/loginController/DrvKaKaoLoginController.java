package project.carPooling.driver.controller.loginController;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.carPooling.driver.service.DrvKakaoService;

import org.springframework.beans.factory.annotation.Autowired;




@Controller
@RequestMapping("/driver/login")
public class DrvKaKaoLoginController {
	
//	 private KakaoLogin kakao_restapi = new KakaoLogin();
	
	@Autowired
	private DrvKakaoService dks;

    @GetMapping("/kakao")
    public String loginPage() {
        return "driver/login/dLoginMain";
    }
    
    @GetMapping("/kakao/redirect")
    public String loginKakao(@RequestParam(value = "code", required = false) String code) throws Exception {
		System.out.println("code=" + code);
		
		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = dks.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);
		
		HashMap<String, Object> userInfo = dks.getKaKaoUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));
    	return "driver/login/dKakaoCallback";
    }

}

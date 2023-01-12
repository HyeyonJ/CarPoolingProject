package project.carPooling.driver.controller.userInfo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.service.DriverUserService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.gmail.UserMailService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvVerifyController {
	
	@Autowired    
    private RedisTemplate<String, String> redisTemplate; 

	private final UserMailService userMailService;
	private final DriverUserService dUserService;
	
	//계좌번호 중복 체크
	@GetMapping("/check/accountInfo")
	public boolean driverCheckAccountInfo(@RequestParam String carNum) {
		boolean checkCarNum = dUserService.driverCheckCarNum(carNum);
		log.info("자동차번호 중복 체크 : {}", checkCarNum);
		return checkCarNum;
	}
	
	//자동차번호 중복 체크
	@GetMapping("/check/carNum")
	public boolean driverCheckCarNum(@RequestParam String carNum) {		
		boolean checkCarNum = dUserService.driverCheckCarNum(carNum);		
		log.info("자동차번호 중복 체크 : {}", checkCarNum);
		return checkCarNum;
	}
	
	//주민등록번호 중복 체크
	@GetMapping("/check/idNum")
	public boolean driverCheckIdNum(@RequestParam String idNum) {		
		boolean checkIdNum = dUserService.driverCheckIdNum(idNum);		
		log.info("주민등록번호 중복 체크 : {}", checkIdNum);
		return checkIdNum;
	}

	//면허번호 중복 체크
	@GetMapping("/check/license")
	public boolean driverCheckLicenseNum(@RequestParam String licenseNum) {		
		boolean checkLicenseNum = dUserService.driverCheckLicenseNum(licenseNum);		
		log.info("면허번호 중복 체크 : {}", checkLicenseNum);
		return checkLicenseNum;
	}
	
	//아이디 중복 체크
	@GetMapping("/check/id")
	public boolean driverCheckJoinId(@RequestParam String id) {
		boolean checkId = dUserService.driverCheckId(id);		
		log.info("아이디 중복 체크 : {}", checkId);
		return checkId;
	}
	
	//이메일 중복 체크
	@GetMapping("/check/email")
	public boolean driverCheckJoinMail(@RequestParam String email) {		
		boolean checkEmail = dUserService.driverCheckEmail(email);		
		log.info("이메일 중복 체크 : {}", checkEmail);
		return checkEmail;
	}
	
	//회원가입 인증 메일 발송
	@GetMapping("/send/email")
	public void driverSendJoinMail(@RequestParam String email) throws MessagingException, IOException {
        MailTO mailTO = new MailTO();
        mailTO.setAddress(email);    //입력받은 이메일 주소    
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");	// 이메일 제목
        mailTO.setMessage("인증번호");		// 이메일 내용
        userMailService.sendMailWithFiles(mailTO);
    }
	
    //driver 회원가입 인증코드 일치여부 확인
	@GetMapping("/check/vCode")
    public boolean driverCheckVcode(@RequestParam String code) {
		boolean checkVcode = userMailService.checkVcode(code);
		
		return checkVcode;
    }
	
	// 테스트 메일 발송
	@GetMapping("/members/email/{email}")
	public String authEmail(@PathVariable String email) throws MessagingException, IOException {
		MailTO mailTO = new MailTO();
        mailTO.setAddress(email);    //입력받은 이메일 주소    
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");	// 이메일 제목
        mailTO.setMessage("인증번호");		// 이메일 내용

        userMailService.sendMailWithFiles(mailTO);
        
		return "driver/join/general";
	}
	
	
	//테스트 redis value 출력
	@GetMapping("/members/email/dancingfrogs@naver.com/{key}")
	public String authEmailtest(@PathVariable String key) {
		log.info("test Redis 실행");
		ValueOperations<String, String> vop = redisTemplate.opsForValue();        
        String value = vop.get(key);
		log.info("value : {}", value);
		return value;
	}

}

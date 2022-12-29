package project.carPooling.passenger.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.gmail.UserMailService;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.service.PassengerUserService;
import project.carPooling.passenger.validation.PassengerValidator;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgMailController {
	
	@Autowired    
    private RedisTemplate<String, String> redisTemplate; 

	private final UserMailService userMailService;
	private final PassengerUserService pUserService;

	//아이디 중복 체크
	@GetMapping("/join/id/check")
	public boolean passengerCheckJoinId(@RequestParam String id) {		
		boolean checkId = pUserService.passengerIdCheck(id);		
		log.info("아이디 중복 체크 : {}", checkId);
		return checkId;
	}
	
	//이메일 중복 체크
	@GetMapping("/join/email/check")
	public boolean passengerCheckJoinMail(@RequestParam String email) {		
		boolean checkEmail = pUserService.passengerEmailCheck(email);		
		log.info("이메일 중복 체크 : {}", checkEmail);
		return checkEmail;
	}
	
	//회원가입 인증 메일 발송
	@GetMapping("/join/email/send")
	public void passengerSendJoinMail(@RequestParam String email) throws MessagingException, IOException {
        MailTO mailTO = new MailTO();
        mailTO.setAddress(email);    //입력받은 이메일 주소    
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");	// 이메일 제목
        mailTO.setMessage("인증번호");		// 이메일 내용
        userMailService.sendMailWithFiles(mailTO);
    }
	
    //passenger 회원가입 인증코드 일치여부 확인
	@GetMapping("/join/vCode/check")
    public boolean checkPassengerVcode(@RequestParam String code) {
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
        
		return "passenger/join/general";
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

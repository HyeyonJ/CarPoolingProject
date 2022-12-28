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
import project.carPooling.global.gmail.JoinMailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.service.PassengerJoinService;
import project.carPooling.passenger.validation.PassengerValidator;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgMailController {
	
	@Autowired    
    private RedisTemplate<String, String> redisTemplate; 

	private final JoinMailService joinMailService;
	private final PassengerJoinService pJoinService;

	
	//email 중복 체크
	@ResponseBody
	@GetMapping("/join/check")
	public boolean checkJoinMail(@RequestParam String email) {
		
		boolean checkEmail = pJoinService.passengerEmailCheck(email);		
		log.info("checkEmail: {}", checkEmail);
		
		return checkEmail;
	}
	
	//가입 인증 메일 발송
	@ResponseBody
	@GetMapping("/join/mail")
	public void sendJoinMail(@RequestParam String email) throws MessagingException, IOException {
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");
        // 이메일 내용
        mailTO.setMessage("인증번호");

        joinMailService.sendMailWithFiles(mailTO);
    }
	
	@GetMapping("/members/email/{email}")
	public String authEmail(@PathVariable String email) throws MessagingException, IOException {
		MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");
        // 이메일 내용
        mailTO.setMessage("인증번호");

        joinMailService.sendMailWithFiles(mailTO);
        
		return "passenger/join/general";
	}
	
	@GetMapping("/members/email/send/{key}")
	public String authEmailtest(@PathVariable String key) {
		log.info("test Redis");
		ValueOperations<String, String> vop = redisTemplate.opsForValue();        
        String value = vop.get(key);
		log.info(value);
		return "passenger/join/general";
	}

	@ModelAttribute("pUserTypes")
	public PUserType[] PUserTypes() {
		return PUserType.values();
	}
	
}

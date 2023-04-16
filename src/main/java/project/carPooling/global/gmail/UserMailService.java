package project.carPooling.global.gmail;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserMailService {	  
	
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	//메일 발송된 인증코드 일치여부 확인
	public boolean checkVcode(String code) {
		boolean checkVcode = false;		
			log.info("입력받은 인증코드 : {}", code);
    	ValueOperations<String, String> vop = redisTemplate.opsForValue();
    	String email = vop.get(code);
    	   	log.info("해당 코드 수신 이메일 : {}",email);
    	if (email != null) {
    		checkVcode = true;
    	} else {
    		log.error("code 불일치");
    	}
    	return checkVcode;
	}
	
    // 인증 코드 + 파일(이미지) 메일 발송
    public void sendMailWithFiles(MailTO mail) throws MessagingException, IOException {
    	String randomPassword = getRamdomPassword(10);
    	log.info("인증 코드 생성 완료");
        
    	MailHandler mailHandler = new MailHandler(mailSender);
    	System.out.println(randomPassword + " / " + mail.getAddress() + " / " + 60*5L);
    	    	
        mailHandler.setTo(mail.getAddress());
        mailHandler.setSubject(mail.getTitle());
        
        // cid:Inline의 key값
//        String htmlContent = "<p>" + mail.getMessage() + " : " + randomPassword + "<p> <img src='cid:joinMail'>";
        String htmlContent = "<p>" + mail.getMessage() +" : <strong>"+ randomPassword + "</strong><p>";
        mailHandler.setText(htmlContent, true);
//        mailHandler.setAttach("txt", "static/test.txt");
        // (Inline의 key값 : "joinMail", "업로드파일경로")
//        mailHandler.setInline("joinMail", "static/img/city.jpeg");
        mailHandler.send();
        
        log.info("가입 인증 메일 발송 완료");
        
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(60*5L);
        vop.set(randomPassword, mail.getAddress(), expireDuration);
        
        log.info("인증 코드 redis set 완료");
        
    }
    
    //인증코드 랜덤 생성
    public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z' };

		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {

			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}
}
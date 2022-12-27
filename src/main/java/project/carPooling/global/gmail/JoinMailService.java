package project.carPooling.global.gmail;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.gmail.MailHandler;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Component
@RequiredArgsConstructor
public class JoinMailService {	

	  
	@Autowired    
    private JavaMailSender mailSender;
    private RedisTemplate<String, String> redisTemplate;
	private PassengerInfoRepository passengerRepository;
    private DriverInfoRepository driverRepository;
    
//    public void setDriverEmailVerifyByCode(String code) {
//    	String email = redisUtil.getData(code);
//    	if(email == null) {
////    		throw new EmailCodeException();
//    		log.error("EmailByCode is null");
//    	}
//    	DriverInfo driver = driverRepository.selectByLoginId(email);
//    	driver.setDUserVerify(true);
//    	log.info("운전자 가입 인증 완료");
//    }

//    public void setPassengerEmailVerifyByCode(String code) {
//    	
//    	if(email == null) {
////    		throw new EmailCodeException();
//    		log.error("EmailByCode is null");
//    	}
//    	PassengerInfo passenger = passengerRepository.selectByLoginId(email);
//    	passenger.setPUserVerify(true);
//    	log.info("승객 가입 인증 완료");
//    }

    public void sendMailWithFiles(MailTO mail) throws MessagingException, IOException {
    	String randomPassword = getRamdomPassword(10);
        
    	MailHandler mailHandler = new MailHandler(mailSender);
    	System.out.println(randomPassword + mail.getAddress() + 60*5L);
    	    	
        mailHandler.setTo(mail.getAddress());
        mailHandler.setSubject(mail.getTitle());
        
        // cid:Inline의 key값
//        String htmlContent = "<p>" + mail.getMessage() + " : " + randomPassword + "<p> <img src='cid:asd'>";
        String htmlContent = "<p>" + mail.getMessage() +" : <strong>"+ randomPassword + "</strong><p>";
        mailHandler.setText(htmlContent, true);
//        mailHandler.setAttach("txt", "static/test.txt");
        // asd = Inline의 key값
//        mailHandler.setInline("asd", "static/img/city.jpeg");
        mailHandler.send();
        
//        ValueOperations<String, String> vop = redisTemplate.opsForValue();
//        Duration expireDuration = Duration.ofSeconds(60*5L);
//        vop.set(randomPassword, mail.getAddress(), expireDuration);        
        
    }
    
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
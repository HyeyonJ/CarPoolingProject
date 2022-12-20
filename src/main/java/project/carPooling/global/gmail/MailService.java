package project.carPooling.global.gmail;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;


@Component
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailTO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
//        message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }

    public void sendMailWithFiles(MailTO mail) throws MessagingException, IOException {
    	String randomPassword = getRamdomPassword(10);
    	
    	MailHandler mailHandler = new MailHandler(mailSender);

        mailHandler.setTo(mail.getAddress());
        mailHandler.setSubject(mail.getTitle());
        
        // cid:Inline의 key값
        String htmlContent = "<p>" + mail.getMessage() +": "+ randomPassword + "<p> <img src='cid:asd'>";
        mailHandler.setText(htmlContent, true);
        mailHandler.setAttach("txt", "static/test.txt");
        // asd = Inline의 key값
        mailHandler.setInline("asd", "static/img/city.jpeg");
        mailHandler.send();
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
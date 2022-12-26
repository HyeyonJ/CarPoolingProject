package project.carPooling.global.gmail;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	// https://bamdule.tistory.com/238 소스
	// http://localhost:8080/mail/send?email=이메일주소
	@GetMapping("/send")
	public MailTO sendTestMail(String email) {
		MailTO mailTO = new MailTO();

		mailTO.setAddress(email);
		mailTO.setTitle("제목.");
		mailTO.setMessage("내용");

		mailService.sendMail(mailTO);

		return mailTO;
	}

	@GetMapping("/fileSend")
	public MailTO sendTestFileEmail(String email) throws MessagingException, IOException {
		MailTO mailTO = new MailTO();

		mailTO.setAddress(email);
		mailTO.setTitle("제목.");
		mailTO.setMessage("내용");

		mailService.sendMailWithFiles(mailTO);

		return mailTO;
	}
	
	
	
	
}

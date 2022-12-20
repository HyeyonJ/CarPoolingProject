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

    @GetMapping("/send")
    public MailTO sendTestMail(String email) {
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("이메일 제목.");
        // 이메일 내용
        mailTO.setMessage("이메일 내용");

        mailService.sendMail(mailTO);

        return mailTO;
    }
    
    @GetMapping("/fileSend")
    public MailTO sendTestFileEmail(String email) throws MessagingException, IOException {
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("이메일 제목.");
        // 이메일 내용
        mailTO.setMessage("인증번호");

        mailService.sendMailWithFiles(mailTO);

        return mailTO;
    }
}

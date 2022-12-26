package project.carPooling.passenger.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;


@RestController
@RequestMapping("/passenger/join/mail")
public class PsgMailController {

    @Autowired
    private MailService mailService;

//    @GetMapping("/send")
    public MailTO sendTestMail(String email) {
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("이메일 제목");
        // 이메일 내용
        mailTO.setMessage("이메일 내용");

        mailService.sendMail(mailTO);

        return mailTO;
    }
    
    @GetMapping("/send")
    public MailTO sendTestFileEmail(String email) throws MessagingException, IOException {
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        // 이메일 제목
        mailTO.setTitle("카풀링 회원가입 인증 메일입니다.");
        // 이메일 내용
        mailTO.setMessage("인증번호");

        mailService.sendMailWithFiles(mailTO);

        return mailTO;
    }
}

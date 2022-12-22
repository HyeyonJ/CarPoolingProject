package project.carPooling.global.gmail;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/mail")
public class MailController {
//
//    @AUTOWIRED
//    PRIVATE MAILSERVICE MAILSERVICE;
//
//    @GETMAPPING("/SEND")
//    PUBLIC MAILTO SENDTESTMAIL(STRING EMAIL) {
//        MAILTO MAILTO = NEW MAILTO();
//
//        MAILTO.SETADDRESS(EMAIL);
//        // 이메일 제목
//        MAILTO.SETTITLE("이메일 제목.");
//        // 이메일 내용
//        MAILTO.SETMESSAGE("이메일 내용");
//
//        MAILSERVICE.SENDMAIL(MAILTO);
//
//        RETURN MAILTO;
//    }
//    
//    @GETMAPPING("/FILESEND")
//    PUBLIC MAILTO SENDTESTFILEEMAIL(STRING EMAIL) THROWS MESSAGINGEXCEPTION, IOEXCEPTION {
//        MAILTO MAILTO = NEW MAILTO();
//
//        MAILTO.SETADDRESS(EMAIL);
//        // 이메일 제목
//        MAILTO.SETTITLE("이메일 제목.");
//        // 이메일 내용
//        MAILTO.SETMESSAGE("인증번호");
//
//        MAILSERVICE.SENDMAILWITHFILES(MAILTO);
//
//        RETURN MAILTO;
//    }
}

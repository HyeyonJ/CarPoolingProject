package project.carPooling.global.gmail;

import java.io.File;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;

public class MailHandler {

//    PRIVATE FINAL JAVAMAILSENDER SENDER;
//    PRIVATE FINAL MIMEMESSAGE MIMEMESSAGE;
//    PRIVATE FINAL MIMEMESSAGEHELPER MIMEMESSAGEHELPER;
//
//    PUBLIC MAILHANDLER(JAVAMAILSENDER JAVAMAILSENDER) THROWS MESSAGINGEXCEPTION {
//        THIS.SENDER = JAVAMAILSENDER;
//        THIS.MIMEMESSAGE = JAVAMAILSENDER.CREATEMIMEMESSAGE();
//        THIS.MIMEMESSAGEHELPER = NEW MIMEMESSAGEHELPER(MIMEMESSAGE, TRUE, "UTF-8");
//    }
//
//    PUBLIC VOID SETFROM(STRING FROMADDRESS) THROWS MESSAGINGEXCEPTION {
//        MIMEMESSAGEHELPER.SETFROM(FROMADDRESS);
//    }
//
//    PUBLIC VOID SETTO(STRING EMAIL) THROWS MESSAGINGEXCEPTION {
//        MIMEMESSAGEHELPER.SETTO(EMAIL);
//    }
//
//    PUBLIC VOID SETSUBJECT(STRING SUBJECT) THROWS MESSAGINGEXCEPTION {
//        MIMEMESSAGEHELPER.SETSUBJECT(SUBJECT);
//    }
//
//    PUBLIC VOID SETTEXT(STRING TEXT, BOOLEAN USEHTML) THROWS MESSAGINGEXCEPTION {
//        MIMEMESSAGEHELPER.SETTEXT(TEXT, USEHTML);
//    }
//
//    PUBLIC VOID SETATTACH(STRING FILENAME, STRING PATH) THROWS MESSAGINGEXCEPTION, IOEXCEPTION {
//        FILE FILE = NEW CLASSPATHRESOURCE(PATH).GETFILE();
//        FILESYSTEMRESOURCE FSR = NEW FILESYSTEMRESOURCE(FILE);
//
//        MIMEMESSAGEHELPER.ADDATTACHMENT(FILENAME, FSR);
//    }
//
//    PUBLIC VOID SETINLINE(STRING FILENAME, STRING PATH) THROWS MESSAGINGEXCEPTION, IOEXCEPTION {
//        FILE FILE = NEW CLASSPATHRESOURCE(PATH).GETFILE();
//        FILESYSTEMRESOURCE FILESYSTEMRESOURCE = NEW FILESYSTEMRESOURCE(FILE);
//
//        MIMEMESSAGEHELPER.ADDINLINE(FILENAME, FILESYSTEMRESOURCE);
//    }
//
//    PUBLIC VOID SEND() {
//        SENDER.SEND(MIMEMESSAGE);
//    }
}

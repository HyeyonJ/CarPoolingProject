package project.carPooling.driver.controller;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.global.session.SessionVar;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if(session == null) { return "home"; }
		
		//이 위치에 session이 존재함
//		for(String sName : session.getAttributeNames()..asIterator())
		Enumeration<String> sessionName = session.getAttributeNames();
		while(sessionName.hasMoreElements()) {
			String name = sessionName.nextElement();
			log.info("session {}, {}", name, session.getAttribute(name));
		}
		
		log.info("{}, {}, {}, {}, {}"
				, session.getId()
				, session.getMaxInactiveInterval()
				, session.getCreationTime()
				, session.getLastAccessedTime()
				, session.isNew());
		
		//sessionManager에서 넘어온 Object가 driver 혹은 passenger인걸 알 수 있으므로 강제 형변환해주기
		DriverInfo driver = (DriverInfo)session.getAttribute(SessionVar.LOGIN_DRIVER);
		
		if(driver == null) { return "home"; }
		
		model.addAttribute("driver", driver);
		
		return "driver/home/dLoginHome";
	}
}

package project.carPooling.global.controller;

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
	public String carPoolingHome(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();
		if (session == null) {
			return "home";
		}
		
		Enumeration<String> sessionName = session.getAttributeNames();
		while(sessionName.hasMoreElements()) {
			String name = sessionName.nextElement();
			log.info("session {}, {}", name, session.getAttribute(name));
		}
		
		log.info("{},{},{},{},{}"
					, session.getId()
					, session.getMaxInactiveInterval()
					, session.getCreationTime()
					, session.getLastAccessedTime()
					, session.isNew());
		
		
		DriverInfo driverInfo = (DriverInfo) session.getAttribute(SessionVar.LOGIN_DRIVER);

		if (driverInfo == null) {
			return "home";
		}
		model.addAttribute("driverInfo", driverInfo);
		return "driver/dRegistration";
	}
}

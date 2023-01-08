package project.carPooling.driver.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvHomeController {

	private final SessionManager sessionManager;
	
	@GetMapping("/home")
	public String driverHome(Model model
							, HttpServletRequest req) {
		
		DriverInfo driver = sessionManager.getDrSession(req);
		
		if(driver == null) { return "redirect:/driver/login"; }
		
		model.addAttribute("driver", driver);
		
		return "driver/home/dHomeMain";
	}
	
}
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
import project.carPooling.global.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvHomeController {

	@GetMapping("/home")
	public String driverHome(Model model
							, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute(SessionVar.LOGIN_DRIVER) == null) {

			return "redirect:/driver/login"; }
		
		//sessionManager에서 넘어온 Object가 driver 혹은 passenger인걸 알 수 있으므로 강제 형변환해주기
		DriverInfo driver = (DriverInfo)session.getAttribute(SessionVar.LOGIN_DRIVER);
		
		if(driver == null) { return "redirect:/driver/login"; }
		
		model.addAttribute("driver", driver);
		
		return "driver/home/dHomeMain";
	}
	
}
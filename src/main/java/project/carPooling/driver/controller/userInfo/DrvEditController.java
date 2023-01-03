package project.carPooling.driver.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvEditController {
	
	private final DriverInfoRepository driverRepository;
	
	@GetMapping("/info")
	public String driverUserInfo (Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute(SessionVar.LOGIN_DRIVER) == null) {
			
			return "redirect:/driver/login"; }

		DriverInfo driver = (DriverInfo)session.getAttribute(SessionVar.LOGIN_DRIVER);
		
		if(driver == null) { return "redirect:/driver/login"; }
		
		model.addAttribute("driver", driver);
		
		return "driver/userInfo/dUserInfo";
	}
	
	@GetMapping("/info/edit")
	public String driverUserInfoEdit (Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute(SessionVar.LOGIN_DRIVER) == null) {
			
			return "redirect:/driver/login"; }

		DriverInfo driver = (DriverInfo)session.getAttribute(SessionVar.LOGIN_DRIVER);
		
		if(driver == null) { return "redirect:/driver/login"; }
		
		model.addAttribute("driver", driver);
		
		return "driver/userInfo/dEditUserInfo";
	}

}

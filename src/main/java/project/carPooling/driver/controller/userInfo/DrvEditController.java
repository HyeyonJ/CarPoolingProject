package project.carPooling.driver.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DUserType;
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

		DriverInfo driverInfo = (DriverInfo)session.getAttribute(SessionVar.LOGIN_DRIVER);
		
		if(driverInfo == null) { return "redirect:/driver/login"; }
		model.addAttribute("driver", driverInfo);
		
		return "driver/userInfo/dUserInfo";
	}
	
	@PostMapping("/info/edit")
	public String driverUserInfoEdit(@ModelAttribute DriverInfo driverInfo, Model model
					, HttpServletRequest req) {
		
		System.out.println("driverInfo : " + driverInfo);

		driverRepository.update(driverInfo);
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
		return "driver/userInfo/dEditUserInfo";
	}
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}

}

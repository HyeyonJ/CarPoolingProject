package project.carPooling.driver.controller.socialLogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.service.DrvKakaoService;
import project.carPooling.global.session.SessionVar;

@RequestMapping("/driver/login")
@Controller
@RequiredArgsConstructor
public class DrvGoogleLoginController {

	private final DriverInfoRepository driverInfoRepository;
	
	@RequestMapping(value = "/google/redirect", method = RequestMethod.POST)
	public String loginGoogle(HttpServletRequest req, RedirectAttributes rAttr) {
		System.out.println("들어옴");
		String email = req.getParameter("email");
		rAttr.addFlashAttribute("dUserEmail", email);
		System.out.println("google dUserEmail: " + email);
		
		DriverInfo driverInfo = driverInfoRepository.selectByEmail(email);
		
		if ( driverInfo == null ) {
			return "redirect:/driver/login/google/redirect";
		}
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);

		return "driver/dRegistration";
	}
	
	@RequestMapping(value="/google/redirect")
	public String loginGoogle2(Model model) {
		
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute("driverInfo", driverInfo);
		
		return "/driver/join/dGoogleCallback";
	}

	@PostMapping("/google/join")
	public String GoogleInsert(@ModelAttribute DriverInfo driverInfo
								, HttpServletRequest req) {
		System.out.println("driverInfo : " + driverInfo);
		System.out.println("---------------------------");
		
		driverInfoRepository.insert(driverInfo);
		
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
		return "driver/dRegistration";
	}
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}
	
}

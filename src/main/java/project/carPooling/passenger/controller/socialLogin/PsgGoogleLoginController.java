package project.carPooling.passenger.controller.socialLogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

	
	@RequestMapping("/passenger/login")
	@Controller
	@RequiredArgsConstructor
	public class PsgGoogleLoginController {

		private final PassengerInfoRepository passengerInfoRepository;
		
		@RequestMapping(value = "/google/redirect", method = RequestMethod.POST)
		public String loginGoogle(HttpServletRequest req, RedirectAttributes rAttr) {
			System.out.println("google Psg login");
			String email = req.getParameter("email");
			rAttr.addFlashAttribute("pUserEmail", email);
			System.out.println("google pUserEmail: " + email);
			
//			DriverInfo driverInfo = driverInfoRepository.selectByEmail(email);
			PassengerInfo passengerInfo = passengerInfoRepository.selectByEmail(email);
			
			if ( passengerInfo == null) {
				return "redirect:/passenger/login/google/redirect";
			}
			
//			if ( driverInfo == null ) {
//				return "redirect:/driver/login/google/redirect";
//			}
			HttpSession session = req.getSession();
//			session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
			session.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo);

			return "passenger/pReservation";
		}
		
		@RequestMapping(value="/google/redirect")
		public String loginGoogle2(Model model) {
			
//			DriverInfo driverInfo = new DriverInfo();
//			model.addAttribute("driverInfo", driverInfo);
//			
//			return "/driver/join/dGoogleCallback";
			
			PassengerInfo passengerInfo = new PassengerInfo();
			model.addAttribute("passengerInfo", passengerInfo);
			
			return "/passenger/join/pGoogleCallback";
		}

		@PostMapping("/google/join")
		public String GoogleInsert(@ModelAttribute PassengerInfo passengerInfo
									, HttpServletRequest req) {
			System.out.println("PassengerInfo : " + passengerInfo);
			System.out.println("---------------------------");
			
//			driverInfoRepository.insert(driverInfo);
			
			passengerInfoRepository.insert(passengerInfo);
			
			HttpSession session = req.getSession();
			session.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo);
			
			return "driver/dRegistration";
		}
		
		@ModelAttribute("pUserTypes")
		public PUserType[] PUserTypes() {
			return PUserType.values();
		}
}

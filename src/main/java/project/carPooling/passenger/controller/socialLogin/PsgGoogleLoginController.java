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
			
			PassengerInfo passengerInfo = passengerInfoRepository.selectByEmail(email);
			
			if(passengerInfo != null && passengerInfo.getPSignOut() == true) {
				rAttr.addFlashAttribute("signOut", true);
				return "redirect:/passenger/login";
			}
			
			if (passengerInfo == null) {
				return "redirect:/passenger/login/google/redirect";
			}
			
			HttpSession session = req.getSession();
			session.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo);
			session.setAttribute(SessionVar.LOGIN_ID, passengerInfo.getPIdx());
			session.setAttribute(SessionVar.LOGIN_NAME, passengerInfo.getPUserName());

			return "redirect:/passenger/passengerCarpool/reservation";
		}
		
		@RequestMapping(value="/google/redirect")
		public String loginGoogle2(Model model) {
			
			PassengerInfo passengerInfo = new PassengerInfo();
			model.addAttribute("passengerInfo", passengerInfo);
			
			return "/passenger/join/pGoogleCallback";
		}

		@PostMapping("/google/join")
		public String GoogleInsert(@ModelAttribute PassengerInfo joinData
									, HttpServletRequest req) {
			
			passengerInfoRepository.insert(joinData);
			PassengerInfo passengerInfo = passengerInfoRepository.selectByEmail(joinData.getPUserEmail());
			
			HttpSession session = req.getSession();
			session.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo);
			
			return "redirect:/passenger/passengerCarpool/reservation";
		}
		
		@ModelAttribute("pUserTypes")
		public PUserType[] PUserTypes() {
			return PUserType.values();
		}
}

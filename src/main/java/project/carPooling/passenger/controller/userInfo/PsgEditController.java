package project.carPooling.passenger.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PUserType;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PsgEditController {
	
	
	private final PassengerInfoRepository passengerInfoRepository;
	private final SessionManager sessionManager;
	
	// 회원 정보 수정 페이지에 값 가지고 오기
	@GetMapping("/info")
	public String passengerUserInfo(Model model, HttpServletRequest req) {
		
		PassengerInfo passenger = sessionManager.getPsSession(req);
		passenger = passengerInfoRepository.selectByEmail(passenger.getPUserEmail());
		
		model.addAttribute("passenger", passenger);
		
		return "passenger/userInfo/pUserInfo";
	}
	
	// 회원 정보 수정 페이지에서 update 후 DB repository에 저장
	@PostMapping("/info")
	public String passengerUserInfoEdit(@ModelAttribute PassengerInfo passenger, Model model
					, HttpServletRequest req) {
		
		System.out.println("passenger : " + passenger);

		passengerInfoRepository.updatePassengerInfo(passenger);
		
//		HttpSession session = req.getSession();
//		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
//		return "driver/userInfo/dEditUserInfo";
//		return "redirect:/driver/userInfo/dUserInfo";
		return "redirect:/passenger/info";
//		return "redirect:/";
	}
	
	// 회원 정보 수정 시 uUserType
	@ModelAttribute("pUserTypes")
	public PUserType[] PUserTypes() {
		return PUserType.values();
	}
	
	// 회원 탈퇴 페이지에 값 가지고 오기
	@GetMapping("/signOut")
	public String passengerSignOut(Model model, HttpServletRequest req) {
		
		PassengerInfo passenger = sessionManager.getPsSession(req);
		passenger = passengerInfoRepository.selectByEmail(passenger.getPUserEmail());
		
		model.addAttribute("passenger", passenger);
		
		return "passenger/userInfo/pSignOut";
	}
	
//	// 회원 탈퇴 시 비밀번호 값 확인
	@ResponseBody
	@PostMapping("/check/signOut")
	public boolean passengerCheckSignOut(@RequestParam String password, HttpServletRequest req) {
//		boolean checkPw = dUserService.driverCheckPw(password);
//		log.info("비밀번호 중복 체크 : {}", checkPw);
//		return checkPw;

		boolean checkPw = false;
		
		PassengerInfo sessionData = sessionManager.getPsSession(req);
		PassengerInfo passenger = passengerInfoRepository.selectByEmail(sessionData.getPUserEmail());
		
		
		if (passenger.getPUserPw().equals(password)) {			
			checkPw = true;	
		}
		return checkPw;
	}

	// 회원 탈퇴 페이지에서 update 후 DB repository에 저장
	@PostMapping("/signOut")
	public String driverSignOutReal(HttpServletRequest req) {
		
		PassengerInfo sessionData = sessionManager.getPsSession(req);
		passengerInfoRepository.updatePassengerSignOut(sessionData.getPUserEmail());
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/";

	}
	
}

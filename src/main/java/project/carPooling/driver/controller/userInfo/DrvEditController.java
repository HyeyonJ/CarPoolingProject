package project.carPooling.driver.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.service.DriverUserService;
import project.carPooling.global.session.SessionManager;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvEditController {
	
	private final DriverInfoRepository driverInfoRepository;
	private final SessionManager sessionManager;
	private final DriverUserService dUserService;
	
	// 회원 정보 수정 페이지에 값 가지고 오기
	@GetMapping("/info")
	public String driverUserInfo(Model model, HttpServletRequest req) {
		
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		driverInfo = driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
		
		model.addAttribute("driverInfo", driverInfo);
		
		return "driver/userInfo/dUserInfo";
	}
	
	// 회원 정보 수정 페이지에서 update 후 DB repository에 저장
	@PostMapping("/info")
	public String driverUserInfoEdit(@ModelAttribute DriverInfo driverInfo, Model model
					, HttpServletRequest req) {
		
		System.out.println("driverInfo : " + driverInfo);

		driverInfoRepository.updateDriverInfo(driverInfo);
		
//		HttpSession session = req.getSession();
//		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
//		return "driver/userInfo/dEditUserInfo";
//		return "redirect:/driver/userInfo/dUserInfo";
		return "redirect:/driver/info";
//		return "redirect:/";
	}
	
	// 회원 정보 수정 시 uUserType
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}
	
	// 회원 탈퇴 페이지에 값 가지고 오기
	@GetMapping("/signOut")
	public String driverSignOut(Model model, HttpServletRequest req) {
		
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		driverInfo = driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
		
		model.addAttribute("driverInfo", driverInfo);
		
		return "driver/userInfo/dSignOut";
	}
	
//	// 회원 탈퇴 시 비밀번호 값 확인
	@ResponseBody
	@PostMapping("/check/signOut")
	public boolean driverCheckSignOut(@RequestParam String password, HttpServletRequest req) {
//		boolean checkPw = dUserService.driverCheckPw(password);
//		log.info("비밀번호 중복 체크 : {}", checkPw);
//		return checkPw;

		boolean checkPw = false;
		
		DriverInfo sessionData = sessionManager.getDrSession(req);
		DriverInfo driver = driverInfoRepository.selectByEmail(sessionData.getDUserEmail());
		
		if (driver.getDUserPw().equals(password)) {			
			checkPw = true;	
		}
		return checkPw;
	}

	// 회원 탈퇴 페이지에서 update 후 DB repository에 저장
	@PostMapping("/signOut")
	public String driverSignOutReal(HttpServletRequest req) {
		DriverInfo sessionData = sessionManager.getDrSession(req);
		driverInfoRepository.updateDriverSignOut(sessionData.getDUserEmail());
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/";

	}

}

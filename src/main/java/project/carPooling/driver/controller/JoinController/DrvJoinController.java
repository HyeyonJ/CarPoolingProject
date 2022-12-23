package project.carPooling.driver.controller.JoinController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.MybatisDriverInfoRepository;
import project.carPooling.driver.validation.DriverValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvJoinController {
	
	// 나중에 할 일
//	private final ListMemberRepository memberRepository;
//	private final MybatisMemberRepository memberRepository;
	
	private final MybatisDriverInfoRepository mybatisDriverInfoRepository;
	private final DriverValidator driverValidator;
	
	@GetMapping("/join")
	public String JoinMain (Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute(driverInfo);
		
		return "driver/join/dJoinMain";
	}

	@PostMapping("/join/general")
	public String newMemberInsert(@ModelAttribute DriverInfo driverInfo
			, BindingResult bindingResult) {
		log.info("member = {}", driverInfo);
		
		driverValidator.validate(driverInfo, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "driver/join/dJoinGeneral";
		}
		
		mybatisDriverInfoRepository.insert(driverInfo);
		return "redirect:/";
	}
	
	@GetMapping("/join/general")
	public String JoinGeneral (Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute("driverInfo", driverInfo);
		
		return "driver/join/dJoinGeneral";
	}
	
	
	@GetMapping("/join/naver/add")
	public String JoinAdd(Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute(driverInfo);
		
		return "driver/join/dJoinAdd";
	}
	
//	@PostConstruct
//	public void init() {
//		DriverInfo driverInfo = new DriverInfo();
//		driverInfo.setUserId("admin");
//		driverInfo.setUserPw("admin");
//		driverInfo.setUserName("관리자");
//
//		mybatisDriverInfoRepository.insert(driverInfo);
//	}
	
}

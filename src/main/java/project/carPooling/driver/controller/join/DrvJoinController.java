package project.carPooling.driver.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.validation.DriverValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/join")
public class DrvJoinController {
	
	private final DriverInfoRepository driverRepository;
	private final DriverValidator driverValidator;
	
	@GetMapping("/general")
	public String newDriver(Model model) {
		DriverInfo driver = new DriverInfo();
		model.addAttribute("driver", driver);
		return "driver/join/dJoinGeneral";
	}
	
	@PostMapping("/general")
	public String newDriverInsert(@ModelAttribute DriverInfo driver
									, BindingResult bindingResult){
		driverValidator.validate(driver, bindingResult);
		//validator에서 DriverInfo 검증 후 bindingResult 넘겨받고
		//error 있으면, 회원가입페이지
		if (bindingResult.hasErrors()) {
			return "driver/join/dJoinGeneral";
		}
		//error 없으면, insert() 수행 후 로그인 페이지
		driverRepository.insert(driver);

		return "redirect:/driver/login";
	}
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}

	
// 소셜 로그인 추가 정보 등록 시
	@PostMapping("/add")
	public String socialLoginInsert(@ModelAttribute DriverInfo driverInfo, BindingResult bindingResult) {
		System.out.println("driverInfo : " + driverInfo);
		System.out.println("---------------------------");
		
//		memberValidator.validate(member, bindingResult);
		
//		if(bindingResult.hasErrors()) {
//			return "members/newMember";
//		}
		driverInfo.setDUserType(null);
		
//		driverInfoR epository.insert(driverInfo);
		return "driver/dRegistration";
	}

}

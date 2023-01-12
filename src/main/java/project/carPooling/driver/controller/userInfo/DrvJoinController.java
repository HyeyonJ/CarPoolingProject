package project.carPooling.driver.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvJoinController {
	
	private final DriverInfoRepository driverInfoRepository;
	
	@GetMapping("/join")
	public String newDriver(Model model) {
		DriverInfo driverInfo = new DriverInfo();
		model.addAttribute("driverInfo", driverInfo);
		return "driver/join/dJoinGeneral";
	}
	
	@PostMapping("/join")
	public String newDriverInsert(@ModelAttribute DriverInfo driverInfo){
		//insert() 수행 후 로그인 페이지
		driverInfoRepository.insert(driverInfo);
		return "redirect:/driver/login";
	}
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}

}

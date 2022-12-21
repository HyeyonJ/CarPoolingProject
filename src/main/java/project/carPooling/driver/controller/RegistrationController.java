package project.carPooling.driver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegistrationController {
	
	@GetMapping("/driver/driverCarpool/registration")
	public String registraion() {
		return "driver/dRegistration";
	}
	
	@RequestMapping(value = "/driver/driverCarpool/registration", method = RequestMethod.POST)
	@ResponseBody
	public void registraionForm(String d_date) {
		System.out.println(d_date);
	}
}

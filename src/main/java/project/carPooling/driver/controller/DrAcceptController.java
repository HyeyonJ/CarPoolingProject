package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.repository.AcceptRepository;

@RequiredArgsConstructor
@Controller
public class DrAcceptController {

	private final AcceptRepository acceptRepository;
	
	@RequestMapping(value="/driver/driverCarpool/reqList", method = RequestMethod.POST)
	public boolean reqConfirm(@RequestBody String req) {
		boolean result = acceptRepository.update(1);
		return result;
	}
}

package project.carPooling.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import project.carPooling.driver.domain.CarPoolRegistration;
import project.carPooling.passenger.domain.CarPoolReservation;

@Controller
public class PsReservationController {
	
	@GetMapping("/passenger/passengerCarpool/reservation")
	public String registraion() {
		return "passenger/pReservation";
	}
	
	@PostMapping("/passenger/passengerCarpool/reservation")
	public String registraionForm(@ModelAttribute CarPoolReservation carPoolReservation) {
//		System.out.println(carPoolRegistration.toString());
		return "passenger/pReservation";
	}
	
}

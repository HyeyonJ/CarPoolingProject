package project.carPooling.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.carPooling.passenger.domain.PReservation;

@Controller
public class PsReservationController {
	
//	@GetMapping("/passenger/passengerCarpool/search")
	@RequestMapping(value="/passenger/passengerCarpool/search", method = RequestMethod.POST)
	@ResponseBody
	public PReservation search(@ModelAttribute PReservation pReservation) {
		System.out.println(pReservation.toString());
		return pReservation;
	}

	@GetMapping("/passenger/passengerCarpool/reservation")
	public String reservation() {
		return "passenger/pReservation";
	}
	
//	@GetMapping("/passenger/passengerCarpool/reservation")
//	public String registraionForm(@ModelAttribute CarPoolReservation carPoolReservation) {
//		System.out.println(carPoolReservation.toString());
//		return "passenger/pReservation";
//	}
	
	
	
}

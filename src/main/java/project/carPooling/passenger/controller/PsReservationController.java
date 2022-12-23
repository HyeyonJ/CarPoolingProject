package project.carPooling.passenger.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;
import project.carPooling.passenger.repository.SearchCarpoolRepository;

@RequiredArgsConstructor
@Controller
public class PsReservationController {
	
	private final SearchCarpoolRepository searchCarpoolRepository;
	
//	@GetMapping("/passenger/passengerCarpool/search")
	@RequestMapping(value="/passenger/passengerCarpool/search", method = RequestMethod.POST)
	@ResponseBody
	public List<DRegistration> search(@ModelAttribute SearchCarPool searchCarPool) {
		System.out.println(searchCarPool.toString());
		List<DRegistration> dRegistrationList = searchCarpoolRepository.selectCarpool(searchCarPool);
		return dRegistrationList;
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

package project.carPooling.passenger.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.repository.ReservationListRepository;

@RequiredArgsConstructor
@Controller
public class PsReservationListController {
	
	private final ReservationListRepository reservationListRepository;
	
	@GetMapping("/passenger/passengerCarpool/reservation/list")
	public String reservationList() {
		return "passenger/pReservationList";
	}
	
	@ResponseBody
	@GetMapping("/passenger/passengerCarpool/reservation/list/{pIdx}")
	public List<Map<String, Object>> confirmedList(@PathVariable("pIdx") Integer pIdx){
		
		List<Map<String, Object>> confirmedList = reservationListRepository.selectConfirmedReservationList(pIdx);
		System.out.println(confirmedList);
		
		return confirmedList;
	}

}

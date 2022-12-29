package project.carPooling.passenger.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.ReservationListRepository;

@RequiredArgsConstructor
@Controller
public class PsReservationListController {
	
	private final ReservationListRepository reservationListRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/passenger/passengerCarpool/reservation/list")
	public String reservationList() {
		return "passenger/pReservationList";
	}
	
	@ResponseBody
	@GetMapping("/passenger/passengerCarpool/reservation/confirmedList")
	public List<Map<String, Object>> confirmedList(HttpServletRequest req){
		
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> confirmedList = reservationListRepository.selectConfirmedReservationList(passengerInfo.getpIdx());
		System.out.println(confirmedList);
		
		return confirmedList;
	}
	
	@ResponseBody
	@GetMapping("/passenger/passengerCarpool/reservation/waitingList")
	public List<Map<String, Object>> waitingList(HttpServletRequest req){
		
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> waitingList = reservationListRepository.selectWaitingReservationList(passengerInfo.getpIdx());
		System.out.println(waitingList);
		
		return waitingList;
	}
	
	@ResponseBody
	@GetMapping("/passenger/passengerCarpool/reservation/pastList")
	public List<Map<String, Object>> pastList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> pastList = reservationListRepository.selectPastReservationList(passengerInfo.getpIdx());
		System.out.println(pastList);
		
		return pastList;
	}

}

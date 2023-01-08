package project.carPooling.passenger.controller.reservation;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/passenger")
public class PsReservationListController {
	
	private final ReservationListRepository reservationListRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/passengerCarpool/list")
	public String reservationList() {
		return "passenger/pReservationList";
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/currentList")
	public List<Map<String, Object>> currentList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> currentList = reservationListRepository.selectCurrentList(passengerInfo.getPIdx());
		System.out.println(currentList);
		
		return currentList;
	}
	
	@ResponseBody
	@DeleteMapping("/passengerCarpool/list/currentList/cancellation")
	public List<Map<String, Object>> cancelCurrentReservation(@RequestParam Integer drIdx, HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> currentList = reservationListRepository.selectCurrentList(passengerInfo.getPIdx());
		System.out.println("여기 drIdx: " + drIdx);
		System.out.println(currentList);
		
		return currentList;
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/pastList")
	public List<Map<String, Object>> pastList(HttpServletRequest req){
		
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> pastList = reservationListRepository.selectPastList(passengerInfo.getPIdx());
		System.out.println(pastList);
		
		return pastList;
	}
	
	@ResponseBody
	@DeleteMapping("/passengerCarpool/reservation/delete")
	public boolean deleteRsv(@RequestParam Integer drIdx, HttpServletRequest req){
		System.out.println(drIdx);
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		boolean result = reservationListRepository.deleteRsv(drIdx, passengerInfo.getPIdx());
		
		return result;
	}

}

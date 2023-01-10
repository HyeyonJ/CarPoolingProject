package project.carPooling.passenger.controller.reservation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.PaymentData;
import project.carPooling.passenger.repository.PassengerPaymentRepository;
import project.carPooling.passenger.repository.ReservationListRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/passenger")
public class PsReservationListController {
	
	private final ReservationListRepository reservationListRepository;
	private final SessionManager sessionManager;
	private final PassengerPaymentRepository passengerPaymentRepository;
	
	@Autowired
	private MailService mailService;
	
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
	public PaymentData cancelCurrentReservation(@RequestParam Integer drIdx) throws MessagingException, IOException{
		String dUserEmail = reservationListRepository.selectDriverEmail(drIdx);
		PaymentData cancelData = passengerPaymentRepository.selectPaymentByDrIdx(drIdx);
		log.info("cancelData : {}",cancelData);
		int dFee = reservationListRepository.cancelCurrentReservation(drIdx);
		cancelData.setCancelAmount(dFee);
		
		MailTO mailTO = new MailTO();

		mailTO.setAddress(dUserEmail);
		mailTO.setTitle("고객님이 등록하신 카풀예약건이 취소되었습니다!");
		mailTO.setMessage("취소를 확인하시려면 이동하기를 눌러주세요.");

		mailService.sendMailWithFiles(mailTO);
		
		return cancelData;
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

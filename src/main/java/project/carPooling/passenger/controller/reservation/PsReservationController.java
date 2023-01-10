package project.carPooling.passenger.controller.reservation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.SearchCarPool;
import project.carPooling.passenger.repository.ReservationRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/passenger")
public class PsReservationController {
	
	private final ReservationRepository reservationRepository;
	private final DriverInfoRepository driverInfoRepository;
	private final SessionManager sessionManager;
	
	@Autowired
	private MailService mailService;
	
	
	@GetMapping("/passengerCarpool/reservation")
	public String reservation() {
		return "passenger/pReservation";
	}
	
	@ResponseBody
	@PostMapping("/passengerCarpool/reservation")
	public Integer reservationForm(@ModelAttribute("drIdx") Integer drIdx, HttpServletRequest req) throws MessagingException, IOException {
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		reservationRepository.insert(passengerInfo.getPIdx(), drIdx);
		Integer rIdx = reservationRepository.selectRIdxByDrIdx(drIdx);
		
		DRegistration drRegistration = driverInfoRepository.selectByDrIdx(drIdx);
		DriverInfo driverInfo = driverInfoRepository.selectByIdx(drRegistration.getDIdx());
		String userEmail = driverInfo.getDUserEmail();
		
		MailTO mailTO = new MailTO();
		
		mailTO.setAddress(userEmail);
		mailTO.setTitle("고객님이 등록하신 카풀에 새로운 예약 요청이 도착했습니다!");
		mailTO.setMessage("요청을 확인하시려면 이동하기를 눌러주세요.");
		
		mailService.sendMailWithFiles(mailTO);
	
		return rIdx;
	}

	@ResponseBody
	@PostMapping("/passengerCarpool/reservation/searchAll")
	public List<DRegistration> search(@ModelAttribute SearchCarPool searchCarPool, HttpServletRequest req) {
		log.info("searchCarPool: {}", searchCarPool.toString());
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<DRegistration> dRegistrationList = reservationRepository.selectCarpool(searchCarPool, passengerInfo.getPIdx());
		return dRegistrationList;
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/reservation/search/{drIdx}")
	public DRegistration reservationForm(@PathVariable("drIdx") Integer drIdx) {
		DRegistration dRegistration = reservationRepository.selectCarpoolByDrIdx(drIdx);
		log.info("dRegistration: {}", dRegistration.toString());
		return dRegistration;
	}
	

}

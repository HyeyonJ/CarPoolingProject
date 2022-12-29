package project.carPooling.passenger.controller.reservation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.SearchCarPool;
import project.carPooling.passenger.repository.SearchCarpoolRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PsReservationController {
	
	private final SearchCarpoolRepository searchCarpoolRepository;
	private final DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private MailService mailService;
	
//	@GetMapping("/passenger/passengerCarpool/search")
	@ResponseBody
	@PostMapping("/passenger/passengerCarpool/search")
	public List<DRegistration> search(@ModelAttribute SearchCarPool searchCarPool) {
		log.info("searchCarPool: {}", searchCarPool.toString());
		List<DRegistration> dRegistrationList = searchCarpoolRepository.selectCarpool(searchCarPool);
		return dRegistrationList;
	}

	@GetMapping("/passenger/passengerCarpool/reservation")
	public String reservation() {
		return "passenger/pReservation";
	}
	
	@ResponseBody
	@GetMapping("/passenger/passengerCarpool/reservation/{drIdx}")
	public DRegistration reservationForm(@PathVariable("drIdx") Integer drIdx) {
		DRegistration dRegistration = searchCarpoolRepository.selectCarpoolByDrIdx(drIdx);
		log.info("dRegistration: {}", dRegistration.toString());
		return dRegistration;
	}
	
	@ResponseBody
	@PostMapping("/passenger/passengerCarpool/reservation/request")
	public void reservationReq(@ModelAttribute DRegistration dRegistration, HttpServletRequest req) throws MessagingException, IOException{
		log.info("dRegistration: {}", dRegistration.toString()); 
		HttpSession session = req.getSession(false);
		PassengerInfo passengerInfo = (PassengerInfo) session.getAttribute(SessionVar.LOGIN_PASSENGER);
		searchCarpoolRepository.insert(passengerInfo.getpIdx(), dRegistration.getDrIdx());
		
		DRegistration drRegistration = driverInfoRepository.selectByDrIdx(dRegistration.getDrIdx());
		DriverInfo driverInfo = driverInfoRepository.selectByIdx(drRegistration.getDIdx());
		String userEmail = driverInfo.getDUserEmail();
		
		MailTO mailTO = new MailTO();

		mailTO.setAddress(userEmail);
		mailTO.setTitle("고객님이 등록하신 카풀에 새로운 예약 요청이 도착했습니다!");
        mailTO.setMessage("요청을 확인하시려면 이동하기를 눌러주세요.");

		mailService.sendMailWithFiles(mailTO);
	}
}

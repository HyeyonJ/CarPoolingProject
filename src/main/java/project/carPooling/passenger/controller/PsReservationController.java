package project.carPooling.passenger.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
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
	@RequestMapping(value="/passenger/passengerCarpool/search", method = RequestMethod.POST)
	@ResponseBody
	public List<DRegistration> search(@ModelAttribute SearchCarPool searchCarPool) {
		log.info("searchCarPool: {}", searchCarPool.toString());
		List<DRegistration> dRegistrationList = searchCarpoolRepository.selectCarpool(searchCarPool);
		return dRegistrationList;
	}

	@GetMapping("/passenger/passengerCarpool/reservation")
	public String reservation() {
		return "passenger/pReservation";
	}
	
	@RequestMapping(value="/passenger/passengerCarpool/reservation/{drIdx}", method = RequestMethod.GET)
	@ResponseBody
	public DRegistration reservationForm(@PathVariable("drIdx") Integer drIdx) {
		DRegistration dRegistration = searchCarpoolRepository.selectCarpoolByDrIdx(drIdx);
		log.info("dRegistration: {}", dRegistration.toString());
		return dRegistration;
	}
	
	@RequestMapping(value="/passenger/passengerCarpool/reservation/request/{pIdx}", method = RequestMethod.POST)
	@ResponseBody
	public String reservationReq(@PathVariable("pIdx") Integer pIdx, @ModelAttribute DRegistration dRegistration) throws MessagingException, IOException{
		log.info("dRegistration: {}", dRegistration.toString()); 
//		searchCarpoolRepository.insert(pIdx, pIdx);
		Integer dIdx = dRegistration.getDIdx();
		
//		DriverInfo driverInfo = driverInfoRepository.selectByIdx(dIdx);
//		String userEmail = driverInfo.getDUserEmail();
		
		MailTO mailTO = new MailTO();

//		mailTO.setAddress(userEmail);
		mailTO.setAddress("ksh990913@naver.com");
		mailTO.setTitle("고객님이 등록하신 카풀에 새로운 예약 요청이 도착했습니다!");
        mailTO.setMessage("요청을 확인하시려면 이동하기를 눌러주세요.");

		mailService.sendMailWithFiles(mailTO);
//		return dRegistration;
		return "ksh990913@naver.com";
	}
}

package project.carPooling.driver.controller.registration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.RegistrationListRepository;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.payment.repository.PaymentRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PaymentData;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DrRegistrationListController {

	private final RegistrationListRepository registrationListRepository;
	private final SessionManager sessionManager;
	private final PaymentRepository PaymentRepository;

	@Autowired
	private MailService mailService;

	@GetMapping("/driverCarpool/list")
	public String registraionList() {
		return "driver/dRegistrationList";
	}
	
	@ResponseBody
	@GetMapping("/driverCarpool/list/reservatedRgsList")
	public List<Map<String, Object>> reservatedRgsList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> reservatedRgsList = registrationListRepository.selectReservatedRgsList(driverInfo.getDIdx());
		return reservatedRgsList;
	}
	

	@ResponseBody
	@GetMapping("/driverCarpool/list/waitingRgsList")
	public List<Map<String, Object>> waitingRgsList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> waitingRgsList = registrationListRepository.selectWaitingRgsList(driverInfo.getDIdx());
		return waitingRgsList;
	}

	
	@ResponseBody
	@GetMapping("/driverCarpool/list/pastRgsList")
	public List<Map<String, Object>> pastRgsList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> pastRgsList = registrationListRepository.selectPastRgsList(driverInfo.getDIdx());
		return pastRgsList;
	}
	
	@ResponseBody
	@GetMapping("/driverCarpool/list/canceledRgsList")
	public List<Map<String, Object>> canceledRgsList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> canceledRgsList = registrationListRepository.selectCanceledRgsList(driverInfo.getDIdx());
		return canceledRgsList;
	}

	
	@ResponseBody
	@PutMapping("/driverCarpool/list/reservatedRgsList/cancellation")
	public PaymentData cancelReservatedRegistration(@RequestParam Integer drIdx, @RequestParam Integer pIdx) throws MessagingException, IOException {
		String pUserEmail = registrationListRepository.selectPassengerEmail(drIdx);
		PaymentData cancelData = PaymentRepository.selectPaymentByDrIdx(drIdx);
		log.info("cancelData : {}",cancelData);
		registrationListRepository.cancelReservatedRegistration(drIdx, pIdx);
		
		MailTO mailTO = new MailTO();
		
		mailTO.setAddress(pUserEmail);
		mailTO.setTitle("고객님이 예약하신 카풀이 취소되었습니다!");
		mailTO.setMessage("취소를 확인하시려면 이동하기를 눌러주세요.");
		
		mailService.sendMailWithFiles(mailTO);
		
		return cancelData;
	}

	@ResponseBody
	@PutMapping("/driverCarpool/list/waitingRgsList/cancellation")
	public boolean cancelWaitingRegistration(@RequestParam Integer drIdx) throws MessagingException, IOException {
		registrationListRepository.updateCanceledRegistration(drIdx);
		return true;
	}


}

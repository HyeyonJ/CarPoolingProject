package project.carPooling.driver.controller.registration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.RegistrationListRepository;
import project.carPooling.driver.repository.RequestRepository;
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.session.SessionManager;

@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DrRegistrationListController {

	private final RequestRepository requestRepository;
	private final RegistrationListRepository registrationListRepository;
	private final SessionManager sessionManager;

	@Autowired
	private MailService mailService;

	@GetMapping("/driverCarpool/reqList")
	public String reqList() {
		return "driver/dRequestList";
	}

	@ResponseBody
	@PostMapping("/driverCarpool/reqList")
	public List<Map<String, Object>> reqList1(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		HttpSession session = req.getSession(false);
//		Integer dIdx = (Integer) session.getAttribute("name");
		Integer dIdx = 1;

		List<Map<String, Object>> reqList = requestRepository.selectRequestByDIdx(dIdx);
		System.out.println(reqList);

		return reqList;
	}


	@GetMapping("/driverCarpool/list")
	public String registraionList() {
		return "driver/dRegistrationList";
	}

	@GetMapping("/driverCarpool/list/reservatedList")
	public String reservatedList() {
		return "driver/dReservatedList";
	}
	
	
	@ResponseBody
	@PostMapping("/driverCarpool/list/reservatedList")
	public List<Map<String, Object>> selectReservatedList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> reservatedList = registrationListRepository
				.selectReservatedList(driverInfo.getDIdx());
		return reservatedList;
	}
	
	@ResponseBody
	@DeleteMapping("/driverCarpool/list/reservatedList/cancellation")
	public boolean cancelReservatedRegistration(@RequestParam Integer drIdx) throws MessagingException, IOException {
		String pUserEmail = registrationListRepository.selectPassengerEmail(drIdx);
		registrationListRepository.cancelReservatedRegistration(drIdx);

		MailTO mailTO = new MailTO();

		mailTO.setAddress(pUserEmail);
		mailTO.setTitle("고객님이 예약하신 카풀이 취소되었습니다!");
		mailTO.setMessage("취소를 확인하시려면 이동하기를 눌러주세요.");

		mailService.sendMailWithFiles(mailTO);

		return true;
	}
	
	@GetMapping("/driverCarpool/list/waitingList")
	public String waitingList() {
		return "driver/dWaitingList";
	}


	@ResponseBody
	@PostMapping("/driverCarpool/list/waitingList")
	public List<Map<String, Object>> waitingList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> waitingList = registrationListRepository.selectWaitingList(driverInfo.getDIdx());
		return waitingList;
	}

	@ResponseBody
	@DeleteMapping("/driverCarpool/list/waitingList/cancellation")
	public boolean cancelWaitingRegistration(@RequestParam Integer drIdx) throws MessagingException, IOException {
		registrationListRepository.cancelWaitingRegistration(drIdx);

		return true;
	}
	
	@GetMapping("/driverCarpool/list/pastList")
	public String pastList() {
		return "driver/dPastList";
	}

	
	@ResponseBody
	@PostMapping("/driverCarpool/list/pastList")
	public List<Map<String, Object>> pastList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> pastList = registrationListRepository.selectPastList(driverInfo.getDIdx());
		return pastList;
	}


}

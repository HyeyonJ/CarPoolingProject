package project.carPooling.passenger.controller.reservation;

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
import project.carPooling.global.gmail.MailService;
import project.carPooling.global.gmail.MailTO;
import project.carPooling.global.payment.repository.PaymentRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.PaymentData;
import project.carPooling.passenger.repository.ReservationListRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/passenger")
public class PsReservationListController {
	
	private final ReservationListRepository reservationListRepository;
	private final SessionManager sessionManager;
	private final PaymentRepository PaymentRepository;
	
	@Autowired
	private MailService mailService;
	
	@GetMapping("/passengerCarpool/list")
	public String reservationList() {
		return "passenger/pReservationList";
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/currentRsvList")
	public List<Map<String, Object>> currentRsvList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> currentRsvList = reservationListRepository.selectCurrentRsvList(passengerInfo.getPIdx());
		System.out.println(currentRsvList);
		for(Map<String, Object> currentRsv : currentRsvList) {
			Integer rIdx = Integer.parseInt(String.valueOf(currentRsv.get("R_IDX")));
			PaymentData paymentData = reservationListRepository.selectPaymentDataByRIdx(rIdx);
			currentRsv.put("receiptUrl", paymentData.getReceiptUrl());
		}
		
		return currentRsvList;
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/pastRsvList")
	public List<Map<String, Object>> pastRsvList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> pastRsvList = reservationListRepository.selectPastRsvList(passengerInfo.getPIdx());
		System.out.println(pastRsvList);
		for(Map<String, Object> pastRsv : pastRsvList) {
			Integer rIdx = Integer.parseInt(String.valueOf(pastRsv.get("R_IDX")));
			PaymentData paymentData = reservationListRepository.selectPaymentDataByRIdx(rIdx);
			pastRsv.put("receiptUrl", paymentData.getReceiptUrl());
		}
		return pastRsvList;
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/completeRsvList")
	public List<Map<String, Object>> completeRsvList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> completeRsvList = reservationListRepository.selectCompleteRsvList(passengerInfo.getPIdx());
		PReview pReview = null;
		for(Map<String, Object> completeRsv : completeRsvList) {
			Integer rIdx = Integer.parseInt(String.valueOf(completeRsv.get("R_IDX")));
			PaymentData paymentData = reservationListRepository.selectPaymentDataByRIdx(rIdx);
			completeRsv.put("receiptUrl", paymentData.getReceiptUrl());
			completeRsv.put("rIdx", rIdx);
			completeRsv.put("pIdx", passengerInfo.getPIdx());
			pReview = reservationListRepository.selectReviewExistStatus(rIdx);
			if(pReview == null) {
				completeRsv.put("pReview", false);
			} else {
				completeRsv.put("pReview", true);
			}
		}
		return completeRsvList;
	}
	
	@ResponseBody
	@GetMapping("/passengerCarpool/list/cancelRsvList")
	public List<Map<String, Object>> cancelRsvList(HttpServletRequest req){
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		List<Map<String, Object>> cancelRsvList = reservationListRepository.selectCancelRsvList(passengerInfo.getPIdx());
		log.info("cancelList: {}", cancelRsvList);
		for(Map<String, Object> cancelRsv : cancelRsvList) {
			Integer rIdx = Integer.parseInt(String.valueOf(cancelRsv.get("R_IDX")));
			log.info("rIdx: {}", rIdx);
			PaymentData paymentData = reservationListRepository.selectPaymentDataByRIdx(rIdx);
			log.info("payIdx: {}", paymentData.getPayIdx());
			Map<String, Object> cancelPayment = reservationListRepository.selectCancelPayMentByPayIdx(paymentData.getPayIdx());
			cancelRsv.put("cancelReceiptUrl", cancelPayment.get("RECEIPT_URL"));
			cancelRsv.put("cancelAmount", cancelPayment.get("CANCEL_AMOUNT"));
		}
		
		return cancelRsvList;
	}
	
	@ResponseBody
	@PutMapping("/passengerCarpool/list/currentRsvList/cancellation")
	public PaymentData cancelCurrentRsv(@RequestParam Integer drIdx, HttpServletRequest req) throws MessagingException, IOException{
		PassengerInfo passengerInfo = sessionManager.getPsSession(req);
		String dUserEmail = reservationListRepository.selectDriverEmail(drIdx);
		PaymentData cancelData = PaymentRepository.selectPaymentByDrIdx(drIdx);
		log.info("cancelData : {}",cancelData);
		int dFee = reservationListRepository.cancelCurrentReservation(drIdx, passengerInfo.getPIdx());
		cancelData.setCancelAmount(dFee);
		
		MailTO mailTO = new MailTO();

		mailTO.setAddress(dUserEmail);
		mailTO.setTitle("고객님이 등록하신 카풀예약건이 취소되었습니다!");
		mailTO.setMessage("취소를 확인하시려면 이동하기를 눌러주세요.");

		mailService.sendMailWithFiles(mailTO);
		
		return cancelData;
	}
	

	
}

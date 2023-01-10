package project.carPooling.passenger.controller.payment;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.PaymentData;
import project.carPooling.passenger.repository.PassengerPaymentRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger/carpoolingPay")
public class PaymentController {
	
	private final PassengerPaymentRepository passengerPaymentRepository;
	private final SessionManager sessionManager;
	

	@GetMapping("/request")
	public PassengerInfo requestPay(HttpServletRequest req) {
		PassengerInfo passenger = sessionManager.getPsSession(req);		
		return passenger;
	}	
	
	@PostMapping("/complete")
    public void completePay(@ModelAttribute PaymentData payData) {
		
		log.info("payData : {}", payData);
    	PaymentData payComplete = (PaymentData)payData;

    	payComplete = passengerPaymentRepository.insertPayment(payComplete);
    	log.info("payComplete : {}", payComplete);

    }

	@PostMapping("/cancel/complete")
	public void completeCancelPay(@ModelAttribute PaymentData cancelData) {
		log.info("cancelData : {}", cancelData);
		
		cancelData = passengerPaymentRepository.insertCancelPayment(cancelData);
		log.info("cancelComplete : {}", cancelData);
		log.info("cancelData insert 완료");
	
	}
	

}

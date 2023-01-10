package project.carPooling.passenger.controller.payment;


import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PaymentData;
import project.carPooling.passenger.repository.PassengerPaymentRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger/carpoolingPay")
public class PaymentController {
	
	private final PassengerPaymentRepository passengerPaymentRepository;
	
	@PostMapping("/complete")
    public void completePay(@RequestBody Map<String, Object> paymentData) {
		
		log.info("paymentData : {}", paymentData);    	
    	PaymentData payComplete = (PaymentData) paymentData;

    	log.info("payComplete : {}", payComplete);    	
    	payComplete = passengerPaymentRepository.insertPayment(payComplete);

    }

//	@PostMapping("/cancelPay")
//	public Map<String, Object> cancelPay(@RequestBody Map<String, Object> paymentData) {
//		log.info("paymentData : {}", paymentData);
//		return paymentData;
//	}
	
	
}

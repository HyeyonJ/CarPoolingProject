package project.carPooling.global.payment;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/passenger/pay")
public class PaymentController {

	private final SessionManager sessionManager;

	@GetMapping("/test")
	public PassengerInfo testRequestPay(HttpServletRequest req) {
		PassengerInfo passenger = sessionManager.getPsSession(req);		
		return passenger;
	}
	
	@PostMapping("/test")
	public Map<String, Object> testResponsePay(@RequestBody Map<String, Object> paymentData) {
		System.out.println(paymentData);
		return paymentData;
	}
	
}

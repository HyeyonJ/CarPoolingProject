package project.carPooling.global.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;

@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger/payTest")
public class paymentController1 {

	private final SessionManager sessionManager;
	
	@GetMapping("/test")
	public String requestPay(Model model, HttpServletRequest req) {		
		PassengerInfo passenger = sessionManager.getPsSession(req);
		if (passenger == null) { return "redirect:/passenger/login"; }
		model.addAttribute("passenger", passenger);
		return "/payment/payment";
	}

}

package project.carPooling.global.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/passenger")
public class paymentController1 {

	private final SessionManager sessionManager;
	
	@GetMapping("/pay")
	public String requestPay(Model model, HttpServletRequest req) {		
		PassengerInfo passenger = sessionManager.getPsSession(req);
		if (passenger == null) { return "redirect:/passenger/login"; }
		model.addAttribute("passenger", passenger);
		return "/payment/payment";
	}
  
//	@GetMapping("/mobile/complete")
//	public String orderCompleteMobile(
//			@RequestParam(required = false) String imp_uid
//			, @RequestParam(required = false) String merchant_uid
//			, Model model
//			, Locale locale
//			, HttpSession session) throws IamportResponseException, IOException
//	{
//		
//		IamportResponse<Payment> result = api.paymentByImpUid(imp_uid);
//		
//		// 결제 가격과 검증결과를 비교한다.
//		if(result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
//			System.out.println("검증통과");
//		}
//		
//		return "redirect:/passenger/passengerCarpool/reservation/list";
//	}
}

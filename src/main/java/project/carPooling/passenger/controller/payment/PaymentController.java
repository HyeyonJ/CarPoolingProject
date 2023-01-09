package project.carPooling.passenger.controller.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.domain.PaymentData;

@Slf4j
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/passenger/carpoolingPay")
public class PaymentController {

	/** Iamport 결제 검증 컨트롤러 **/
    private final IamportClient impClient;
	private final SessionManager sessionManager;
	
	// 생성자를 통해 REST API 와 REST API secret 입력
    public PaymentController(){
        this.impClient = new IamportClient("5051703506060018", "CNUjW9iBntIkvX52eLr2lhoQxrkqhmGVeC7KxGcc7Ry0L0VJd9HFRfhCxVMrTZtc2am1RYKnFvJSQl02");
		this.sessionManager = new SessionManager();
    }

	@GetMapping("/rqPay")
	public PassengerInfo requestPay(HttpServletRequest req) {
		PassengerInfo passenger = sessionManager.getPsSession(req);		
		return passenger;
	}
	
	/** 프론트에서 받은 PG사 결과값을 통해 아임포트 토큰 발행 **/
    @PostMapping("/verifyIamport/{imp_uid}")
    //아임포트서버에서 imp_uid(거래 고유번호)를 검사하여, 데이터를 보내준다.
    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException{
        log.info("paymentByImpUid 진입");
        return impClient.paymentByImpUid(imp_uid);
    }
    
    @GetMapping("/complete")
    public Map<String, Object> completePay(@RequestBody Map<String, Object> paymentData) {
    	log.info("paymentData : {}", paymentData);		
    	return paymentData;
    }
	
	@PostMapping("/cancelPay")
	public Map<String, Object> cancelPay(@RequestBody Map<String, Object> paymentData) {
		log.info("paymentData : {}", paymentData);
		return paymentData;
	}
	
	/** 모바일 결제 시, callback 실행 대신 수행할 redirect url **/
    @GetMapping("/mobile/complete")
	public String orderCompleteMobile(
			@RequestParam(required = false) String imp_uid
			, @RequestParam(required = false) String merchant_uid
			, Model model
			, Locale locale
			, HttpSession session) throws IamportResponseException, IOException
	{
		
		IamportResponse<Payment> result = impClient.paymentByImpUid(imp_uid);
		
		// 결제 가격과 검증결과를 비교한다.
		if(result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
			log.info("검증 결과 : 일치(통과)");
		} else {
			log.error("검증 결과 : 불일치");
		}
		return "redirect:/passenger/passengerCarpool/reservation/list";
	}
	
}

package project.carPooling.global.payments;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentsController {

	
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
//		return "home";
//	}

}

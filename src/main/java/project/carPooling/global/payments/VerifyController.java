package project.carPooling.global.payments;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.siot.IamportRestClient.IamportClient;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/payments")
//public class VerifyController {
//
//    /** Iamport 결제 검증 컨트롤러 **/
//    private final IamportClient iamportClient;
//
//    // 생성자를 통해 REST API 와 REST API secret 입력
//    public VerifyController(){
//        this.iamportClient = new IamportClient("5051703506060018", "CNUjW9iBntIkvX52eLr2lhoQxrkqhmGVeC7KxGcc7Ry0L0VJd9HFRfhCxVMrTZtc2am1RYKnFvJSQl02");
//    }
//
//    /** 프론트에서 받은 PG사 결과값을 통해 아임포트 토큰 발행 **/
//    @PostMapping("/verifyIamport/{imp_uid}")
//    //아임포트서버에서 imp_uid(거래 고유번호)를 검사하여, 데이터를 보내준다.
//    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException{
//        log.info("paymentByImpUid 진입");
//        return iamportClient.paymentByImpUid(imp_uid);
//    }

//}

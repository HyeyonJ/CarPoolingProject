package project.carPooling.passenger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentData {
	private String payIdx;	//merchantUid : 주문(결제요청)번호
	private Integer rIdx;	//카풀예약번호
	private Integer pIdx;	//구매자(탑승객)
	private Integer amount;	//금액
	private Integer cancelAmount;	//결제 취소처리된 환불금액
	private String receiptUrl;	//매출전표 url	

}

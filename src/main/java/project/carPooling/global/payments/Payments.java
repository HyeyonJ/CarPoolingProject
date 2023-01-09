package project.carPooling.global.payments;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Payments {
	private String merchantUid;	//주문(결제요청)번호
	private Integer amount;	//금액
	private String status;	//결제상태
}

package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import project.carPooling.passenger.domain.PaymentData;


public interface PassengerPaymentRepository {
	
	public PaymentData insertPayment(PaymentData payment);
	
	public PaymentData insertCancelPayment(PaymentData payment);
	
	public PaymentData selectPaymentByPayIdx(String payIdx);
	
	public PaymentData selectCancelPaymentByPayIdx(String payIdx);

	public List<Map<String, Object>> selectCompletePayListByPidx(Integer pIdx);
	
	public List<Map<String, Object>> selectCancelPayListByPidx(Integer pIdx);
	
	public void deletePaymentByPayIdx(@Param("payIdx") String payIdx);
	
	public PaymentData selectPaymentByDrIdx(@Param("drIdx") Integer drIdx);
}

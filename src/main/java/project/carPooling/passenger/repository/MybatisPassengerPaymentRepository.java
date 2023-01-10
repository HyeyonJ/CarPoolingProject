package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PaymentData;
import project.carPooling.passenger.mapper.PassengerPaymentMapper;

@Repository
@RequiredArgsConstructor
public class MybatisPassengerPaymentRepository implements PassengerPaymentRepository {
	
	private final PassengerPaymentMapper paymentMapper;

	@Override
	public PaymentData insertPayment(PaymentData payment) {
		paymentMapper.insertPayment(payment);
		return payment;
	}

	@Override
	public PaymentData insertCancelPayment(PaymentData payment) {
		paymentMapper.insertCancelPayment(payment);
		return payment;
	}

	@Override
	public PaymentData selectPaymentByPayIdx(String payIdx) {
		PaymentData payment = paymentMapper.selectPaymentByPayIdx(payIdx);
		return payment;
	}
	
	@Override
	public PaymentData selectCancelPaymentByPayIdx(String payIdx) {
		PaymentData payment = paymentMapper.selectCancelPaymentByPayIdx(payIdx);
		return payment;
	}

	@Override
	public List<Map<String, Object>> selectCompletePayListByPidx(Integer pIdx) {
		List<Map<String, Object>> payList = paymentMapper.selectCompletePayListByPidx(pIdx);
		return payList;
	}

	@Override
	public List<Map<String, Object>> selectCancelPayListByPidx(Integer pIdx) {
		List<Map<String, Object>> payList = paymentMapper.selectCancelPayListByPidx(pIdx);
		return payList;
	}

	@Override
	public void deletePaymentByPayIdx(String payIdx) {
		paymentMapper.deletePaymentByPayIdx(payIdx);
		
	}

	@Override
	public PaymentData selectPaymentByDrIdx(Integer drIdx) {
		PaymentData payment = paymentMapper.selectPaymentByDrIdx(drIdx);
		
		return payment;
	}



}

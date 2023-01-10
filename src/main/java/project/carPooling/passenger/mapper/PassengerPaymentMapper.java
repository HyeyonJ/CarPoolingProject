package project.carPooling.passenger.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import project.carPooling.passenger.domain.PaymentData;

@Mapper
public interface PassengerPaymentMapper {
	
	public Integer insertPayment(PaymentData payment);
	
	public Integer insertCancelPayment(PaymentData payment);
	
	public PaymentData selectPaymentByPayIdx(@Param("payIdx") String payIdx);

	public PaymentData selectCancelPaymentByPayIdx(@Param("payIdx") String payIdx);
	
	public List<Map<String, Object>> selectCompletePayListByPidx(@Param("pIdx") Integer pIdx);
	
	public List<Map<String, Object>> selectCancelPayListByPidx(@Param("pIdx") Integer pIdx);
	
	public void deletePaymentByPayIdx(@Param("payIdx") String payIdx);

}

package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

import project.carPooling.passenger.domain.PaymentData;

public interface ReservationListRepository {
	public List<Map<String, Object>> selectCurrentRsvList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastRsvList(Integer pIdx);
	
	public List<Map<String, Object>> selectCancelRsvList(Integer pIdx);
	
	public int cancelCurrentReservation(Integer drIdx, Integer pIdx);
	
	public String selectDriverEmail(Integer drIdx);
	
	public PaymentData selectPaymentDataByRIdx(Integer rIdx);
	
	public Map<String, Object> selectCancelPayMentByPayIdx(String payIdx);
}

package project.carPooling.passenger.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.PaymentData;

@Mapper
public interface ReservationListMapper {
	public List<Map<String, Object>> selectCurrentRsvList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastRsvList(Integer pIdx);
	
	public List<Map<String, Object>> selectCancelRsvList(Integer pIdx);

	public String selectDriverEmail(Integer drIdx);
	
	public void updateDriverPoint(@Param("dIdx") Integer dIdx, @Param("point") int point);
	
	public void updateWaitingRegistration(Integer drIdx);
	
	public void updateCanceledReservation(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
	
	public DRegistration selectRegistrationByDrIdx(Integer drIdx);
	
	public PaymentData selectPaymentDataByRIdx(Integer rIdx);
	
	public Map<String, Object> selectCancelPayMentByPayIdx(String payIdx);
}

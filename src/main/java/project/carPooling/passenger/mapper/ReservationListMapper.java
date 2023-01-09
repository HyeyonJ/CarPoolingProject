package project.carPooling.passenger.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface ReservationListMapper {
	public List<Map<String, Object>> selectCurrentList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastList(Integer pIdx);

	public void deleteRsv(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
	
	public String selectDriverEmail(Integer drIdx);
	
	public void updateDriverPoint(@Param("dIdx") Integer dIdx, @Param("point") int point);
	
	public void updateReservatedToWaiting(Integer drIdx);
	
	public void deleteReservation(Integer drIdx);
	
	public DRegistration selectRegistrationByDrIdx(Integer drIdx);
}

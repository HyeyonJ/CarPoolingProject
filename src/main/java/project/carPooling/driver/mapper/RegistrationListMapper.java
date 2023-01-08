package project.carPooling.driver.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface RegistrationListMapper {
	public List<Map<String, Object>> selectReservatedList(Integer dIdx);
	
	public List<Map<String, Object>> selectWaitingList(Integer dIdx);

	public List<Map<String, Object>> selectPastList(Integer dIdx);

	public DRegistration selectRegistrationByDrIdx(Integer drIdx);
	
	public void updatePanalty(Integer dIdx);
	
	public void deleteRegistration(Integer drIdx);
	
	public void deleteReservation(Integer drIdx);
	
	public String selectPassengerEmail(Integer drIdx);
	
}

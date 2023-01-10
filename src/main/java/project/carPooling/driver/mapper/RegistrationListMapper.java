package project.carPooling.driver.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface RegistrationListMapper {
	public List<Map<String, Object>> selectReservatedRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectWaitingRgsList(Integer dIdx);

	public List<Map<String, Object>> selectPastRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectCanceledRgsList(Integer dIdx);

	public DRegistration selectRegistrationByDrIdx(Integer drIdx);
	
	public void updatePanalty(Integer dIdx);
	
	public void updateCanceledRegistration(Integer drIdx);
	
	public void updateCanceledReservation(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
	
	public String selectPassengerEmail(Integer drIdx);
	
}

package project.carPooling.passenger.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

@Mapper
public interface ReservationMapper {
	
	public void insert(@Param(value="pIdx") Integer pIdx, @Param(value="drIdx") Integer drIdx);

	public DRegistration selectCarpoolByGender(@Param(value="searchCarPool") SearchCarPool searchCarPool, @Param(value = "pDate") Date pDate, @Param(value="pUserGender") String pUserGender, @Param(value = "dIdx") Integer dIdx);
	
	public List<DRegistration> selectCarpoolByAny(@Param(value="searchCarPool") SearchCarPool searchCarPool, @Param(value = "pDate") Date pDate, @Param(value="pUserGender") String pUserGender);
	
	public DRegistration selectCarpoolByDrIdx(Integer drIdx);

	public List<Integer> selectDIdxByGender(String pHopeGender);

	public String selectPUserGenderByPIdx(Integer pIdx);
	
	public void updateWaitingToReservated(Integer drIdx);
	
	public Integer selectRIdxByDrIdx(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
	
	public DRegistration selectDRegistrationByDrIdx(@Param("drIdx") Integer drIdx, @Param("pIdx") Integer pIdx);
	
}


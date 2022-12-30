package project.carPooling.passenger.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationListMapper {
	public List<Map<String, Object>> selectAcceptedReservationList(@Param("pIdx") Integer pIdx);
	
	public List<Map<String, Object>> selectWaitingReservationList(@Param("pIdx") Integer pIdx);

	public List<Map<String, Object>> selectRefusedReservationList(@Param("pIdx") Integer pIdx);
	
	public List<Map<String, Object>> selectPastReservationList(@Param("pIdx") Integer pIdx);
}

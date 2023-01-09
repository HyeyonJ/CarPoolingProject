package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReservationListRepository {
	public List<Map<String, Object>> selectCurrentList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastList(Integer pIdx);
	
	public boolean deleteRsv(Integer drIdx, Integer pIdx);
	
	public int cancelCurrentReservation(Integer drIdx);
	
	public String selectDriverEmail(Integer drIdx);
}

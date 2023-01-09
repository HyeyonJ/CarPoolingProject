package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

public interface ReservationListRepository {
	public List<Map<String, Object>> selectCurrentList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastList(Integer pIdx);
	
	public boolean deleteRsv(Integer drIdx, Integer pIdx);
	
	public void cancelWaitingReservation(Integer drIdx);
}

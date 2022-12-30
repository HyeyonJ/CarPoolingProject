package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

public interface ReservationListRepository {
	public List<Map<String, Object>> selectAcceptedReservationList(Integer pIdx);
	
	public List<Map<String, Object>> selectWaitingReservationList(Integer pIdx);

	public List<Map<String, Object>> selectRefusedReservationList(Integer pIdx);
	
	public List<Map<String, Object>> selectPastReservationList(Integer pIdx);
}

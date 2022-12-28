package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

public interface ReservationListRepository {
	public List<Map<String, Object>> selectConfirmedReservationList(Integer pIdx);
}

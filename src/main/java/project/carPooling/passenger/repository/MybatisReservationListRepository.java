package project.carPooling.passenger.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.mapper.ReservationListMapper;

@Repository
@RequiredArgsConstructor
public class MybatisReservationListRepository implements ReservationListRepository{

	private final ReservationListMapper reservationListMapper;
	
	@Override
	public List<Map<String, Object>> selectAcceptedReservationList(Integer pIdx) {
		List<Map<String, Object>> acceptedList = reservationListMapper.selectAcceptedReservationList(pIdx);
		return acceptedList;
	}

	@Override
	public List<Map<String, Object>> selectWaitingReservationList(Integer pIdx) {
		List<Map<String, Object>> waitingList = reservationListMapper.selectWaitingReservationList(pIdx);
		return waitingList;
	}

	@Override
	public List<Map<String, Object>> selectRefusedReservationList(Integer pIdx) {
		List<Map<String, Object>> refusedList = reservationListMapper.selectRefusedReservationList(pIdx);
		return refusedList;
	}

	@Override
	public List<Map<String, Object>> selectPastReservationList(Integer pIdx) {
		List<Map<String, Object>> pastList = reservationListMapper.selectPastReservationList(pIdx);
		return pastList;
	}

}

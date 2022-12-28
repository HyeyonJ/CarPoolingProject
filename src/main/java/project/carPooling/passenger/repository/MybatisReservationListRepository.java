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
	public List<Map<String, Object>> selectConfirmedReservationList(Integer pIdx) {
		List<Map<String, Object>> confirmedList = reservationListMapper.selectConfirmedReservationList(pIdx);
		return confirmedList;
	}

}

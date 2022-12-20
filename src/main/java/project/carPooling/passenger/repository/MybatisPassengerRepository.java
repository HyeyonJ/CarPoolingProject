package project.carPooling.passenger.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.mapper.PassengerInfoMapper;

@Slf4j
@Repository
@RequiredArgsConstructor	//Mapper 인터페이스 호출하기 위함
public class MybatisPassengerRepository implements PassengerInfoRepository {
	
	//Mapper 인터페이스 호출
	private final PassengerInfoMapper psgMapper;

	@Override
	public PassengerInfo insert(PassengerInfo passenger) {
		psgMapper.insert(passenger);
		return passenger;
	}

	@Override
	public PassengerInfo selectById(int id) {
		PassengerInfo passenger = psgMapper.selectById(id);
		return passenger;
	}

	@Override
	public PassengerInfo selectByLoginId(String loginId) {
		PassengerInfo passenger = psgMapper.selectByLoginId(loginId);
		return passenger;
	}

	@Override
	public List<PassengerInfo> selectAll() {
		List<PassengerInfo> passengers = psgMapper.selectAll();
		return passengers;
	}

	@Override
	public void deleteAll() {
		psgMapper.deleteAll();		
	}

}

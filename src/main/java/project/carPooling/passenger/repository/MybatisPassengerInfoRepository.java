package project.carPooling.passenger.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.mapper.PassengerInfoMapper;

@Slf4j
@Repository @Primary
@RequiredArgsConstructor	//Mapper 인터페이스 호출하기 위함
public class MybatisPassengerInfoRepository implements PassengerInfoRepository {
	
	//Mapper 인터페이스 호출
	private final PassengerInfoMapper passengerMapper;

	@Override
	public PassengerInfo insert(PassengerInfo passenger) {
		passengerMapper.insert(passenger);
		return passenger;
	}

	@Override
	public PassengerInfo selectById(int id) {
		PassengerInfo passenger = passengerMapper.selectById(id);
		return passenger;
	}

	@Override
	public PassengerInfo selectByLoginId(String loginId) {
		PassengerInfo passenger = passengerMapper.selectByLoginId(loginId);
		return passenger;
	}

	@Override
	public PassengerInfo selectByEmail(String email) {
		PassengerInfo passenger = passengerMapper.selectByEmail(email);
		return passenger;
	}
	

	@Override
	public PassengerInfo selectByNameAndEmail(String name, String email) {
		PassengerInfo passenger = passengerMapper.selectByNameAndEmail(name, email);
		return passenger;
	}

	@Override
	public PassengerInfo selectByNameAndTel(String name, String tel) {
		PassengerInfo passenger = passengerMapper.selectByNameAndTel(name, tel);
		return passenger;
	}

	@Override
	public PassengerInfo selectByNameAndIdnum(String name, String idnum) {
		PassengerInfo passenger = passengerMapper.selectByNameAndIdnum(name, idnum);
		return passenger;
	}
	
	@Override
	public List<PassengerInfo> selectAll() {
		List<PassengerInfo> passengers = passengerMapper.selectAll();
		return passengers;
	}

	@Override
	public void deleteAll() {
		passengerMapper.deleteAll();		
	}

	@Override
	public PassengerInfo update(PassengerInfo passenger) {
		passengerMapper.update(passenger);
		return passenger;
	}




}

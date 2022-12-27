package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.mapper.DriverInfoMapper;

@Repository
@RequiredArgsConstructor	//Mapper 인터페이스 호출하기 위함
@Qualifier
public class MybatisDriverInfoRepository implements DriverInfoRepository {

	//Mapper 인터페이스 호출
	private final DriverInfoMapper driverMapper;
	
	@Override
	public DriverInfo insert(DriverInfo driverInfo) {
		driverMapper.insert(driverInfo);
		return driverInfo;
	}

	@Override
	public DriverInfo selectById(int id) {
		DriverInfo driverInfo = driverMapper.selectById(id);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByLoginId(String loginId) {
		DriverInfo driverInfo = driverMapper.selectByLoginId(loginId);
		return driverInfo;
	}

	@Override
	public List<DriverInfo> selectAll() {
		List<DriverInfo> drivers = driverMapper.selectAll();
		return drivers;
	}

	@Override
	public void deleteAll() {
		driverMapper.deleteAll();
	}

}

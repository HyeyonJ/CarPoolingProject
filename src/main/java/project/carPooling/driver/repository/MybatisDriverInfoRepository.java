package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.mapper.DriverInfoMapper;

// @Primary https://developing-stock-child.tistory.com/78
@Repository @Primary
@RequiredArgsConstructor
public class MybatisDriverInfoRepository implements DriverInfoRepository {

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

	@Override
	public DriverInfo selectByIdx(Integer dIdx) {
		DriverInfo driverInfo = driverMapper.selectByIdx(dIdx);
		return driverInfo;
	}

}

package project.carPooling.driver.repository;

import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
=======
>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.mapper.DriverInfoMapper;

<<<<<<< HEAD
@Slf4j
@Repository @Primary
@RequiredArgsConstructor	//Mapper 인터페이스 호출하기 위함
=======
// @Primary https://developing-stock-child.tistory.com/78
@Repository @Primary
@RequiredArgsConstructor
>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
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

	// 여기에서 email로 값을 받아오는 것을 처리하는 것
//	@Override
//	public DriverInfo selectByEmail (String dUserEmail);
//		DriverInfo driverInfo = driverMapper.selectByLoginId(loginId);
//		return driverInfo;
//}
	
	@Override
	public List<DriverInfo> selectAll() {
		List<DriverInfo> drivers = driverMapper.selectAll();
		return drivers;
	}

	@Override
	public void deleteAll() {
		driverMapper.deleteAll();
	}
<<<<<<< HEAD
=======

	@Override
	public DriverInfo selectByIdx(Integer dIdx) {
		DriverInfo driverInfo = driverMapper.selectByIdx(dIdx);
		return driverInfo;
	}

>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
}

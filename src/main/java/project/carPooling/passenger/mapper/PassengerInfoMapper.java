package project.carPooling.passenger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.passenger.domain.PassengerInfo;

@Mapper
public interface PassengerInfoMapper {
	
	public Integer insert(@Param("passenger") PassengerInfo passsenger);
	
	public PassengerInfo selectById(int id);
	
	public PassengerInfo selectByLoginId(String loginId);
	
	public PassengerInfo selectByEmail(String email);
	
	public PassengerInfo selectByNameAndEmail(String name, String email);

	public PassengerInfo selectByNameAndTel(String name, String tel);
	
	public PassengerInfo selectByNameAndIdnum(String name, String idnum);
	
	public List<PassengerInfo> selectAll();
	
	public void deleteAll();
	
	// 회원 정보 update 처리
	public void updatePassengerInfo(PassengerInfo passenger);
	
	// 회원 탈퇴 시 update 처리
	public void updatePassengerSignOut(String pUserEmail); 
	
	public PassengerInfo update(PassengerInfo passenger);
	
	public PassengerInfo selectByIdNum(String idNum);
	
	public PassengerInfo selectByIdx(Integer pIdx);
	
}

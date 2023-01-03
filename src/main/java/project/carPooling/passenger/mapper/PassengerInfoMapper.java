package project.carPooling.passenger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

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
	
	public PassengerInfo update(PassengerInfo passenger);
	
}

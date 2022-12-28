package project.carPooling.passenger.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.carPooling.passenger.domain.PassengerInfo;

@Mapper
public interface PassengerInfoMapper {
	
	public Integer insert(PassengerInfo passsenger);
	
	public PassengerInfo selectById(int id);
	
	public PassengerInfo selectByLoginId(String loginId);
	
	public PassengerInfo selectByEmail(String email);
	
	public PassengerInfo selectByNameAndEmail(String name, String email);

	public PassengerInfo selectByNameAndTel(String name, String tel);
	
	public PassengerInfo selectByNameAndIdnum(String name, String idnum1, String idnum2);
	
	public List<PassengerInfo> selectAll();
	
	public void deleteAll();
	
	public PassengerInfo update(PassengerInfo passenger);
	
}

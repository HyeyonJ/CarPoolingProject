package project.carPooling.passenger.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.carPooling.passenger.domain.PassengerInfo;

@Mapper
public interface PassengerInfoMapper {
	
	public Integer insert(PassengerInfo passsenger);
	
	public PassengerInfo selectById(int id);
	
	public PassengerInfo selectByLoginId(String loginId);
	
	public List<PassengerInfo> selectAll();
	
	public void deleteAll();
}

package project.carPooling.passenger.repository;

import java.util.List;
import project.carPooling.passenger.domain.PassengerInfo;

public interface PassengerInfoRepository {
	
	public PassengerInfo insert(PassengerInfo passenger);
	
	public PassengerInfo selectById(int id);
	
	public PassengerInfo selectByLoginId(String loginId);
	
	public PassengerInfo selectByEmail(String pUserEmail);
	
	public PassengerInfo selectByNameAndEmail(String name, String email);
	
	public PassengerInfo selectByNameAndTel(String name, String tel);
	
	public PassengerInfo selectByNameAndIdnum(String name, String idnum);
	
	public List<PassengerInfo> selectAll();
	
	public void deleteAll();
	
	public PassengerInfo update(PassengerInfo passenger);

}

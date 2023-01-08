package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;


public interface RegistrationListRepository {
	public List<Map<String, Object>> selectReservatedList(Integer dIdx);
	public List<Map<String, Object>> selectWaitingList(Integer dIdx);
	public List<Map<String, Object>> selectPastList(Integer dIdx);
	public boolean deleteRegistration(Integer drIdx);
	public void cancelReservatedRegistration(Integer drIdx);
	public String selectPassengerEmail(Integer drIdx);
	public void cancelWaitingRegistration(Integer drIdx);
}

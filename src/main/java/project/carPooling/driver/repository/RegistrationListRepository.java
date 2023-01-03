package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

public interface RegistrationListRepository {
	public List<Map<String, Object>> selectAcceptedRegistrationList(Integer dIdx);
	public List<Map<String, Object>> selectAllRegistrationList(Integer dIdx);
	public boolean deleteRegistration(Integer drIdx);
}

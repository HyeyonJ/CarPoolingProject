package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.RegistrationListMapper;

@Repository
@RequiredArgsConstructor
public class MybatisRegistrationListRepository implements RegistrationListRepository{
		
	private final RegistrationListMapper registrationListMapper;
	
	@Override
	public List<Map<String, Object>> selectAcceptedRegistrationList(Integer dIdx) {
		List<Map<String, Object>> acceptedList = registrationListMapper.selectAcceptedRegistrationList(dIdx);
	return acceptedList;
	}

	@Override
	public List<Map<String, Object>> selectAllRegistrationList(Integer dIdx) {
		List<Map<String, Object>> allList = registrationListMapper.selectAllRegistrationList(dIdx);
		return allList;
	}

	@Override
	public boolean deleteRegistration(Integer drIdx) {
		boolean result = false;
		try {
			registrationListMapper.deleteRegistration(drIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}
	
}

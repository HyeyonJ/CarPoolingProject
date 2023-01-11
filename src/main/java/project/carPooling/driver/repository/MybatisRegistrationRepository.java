package project.carPooling.driver.repository;


import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.mapper.RegistrationMapper;

@Repository
@RequiredArgsConstructor	 
public class MybatisRegistrationRepository implements RegistrationRepository {

	private final RegistrationMapper registrationMapper;
	
	@Override
	public void insert(DRegistration dRegistration) {
		java.sql.Date dDate =  java.sql.Date.valueOf(dRegistration.getDDate());
		registrationMapper.insert(dRegistration, dDate);
		System.out.println("카풀등록성공");
	}

	@Override
	public DRegistration selectRegistrationByTime(DRegistration dRegistration) {
		java.sql.Date dDate =  java.sql.Date.valueOf(dRegistration.getDDate());
		DRegistration dr = registrationMapper.selectRegistrationByTime(dRegistration, dDate);
		return dr;
	}


	

}

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
	public DRegistration insert(DRegistration dRegistration) {
		java.sql.Date dDate =  java.sql.Date.valueOf(dRegistration.getDDate());
		dRegistration.setDIdx(1);
		registrationMapper.insert(dRegistration, dDate);
		System.out.println("카풀등록성공");
		return dRegistration;
	}


	

}
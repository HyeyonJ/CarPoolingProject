package project.carPooling.driver.repository;

import project.carPooling.driver.domain.DRegistration;

public interface RegistrationRepository {
	
	public void insert(DRegistration dRegistration);
	public DRegistration selectRegistrationByTime(DRegistration dRegistration);
	
}

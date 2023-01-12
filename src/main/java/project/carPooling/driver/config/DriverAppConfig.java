package project.carPooling.driver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.DriverInfoMapper;
import project.carPooling.driver.mapper.DriverReviewMapper;
import project.carPooling.driver.mapper.DrivingMapper;
import project.carPooling.driver.mapper.RegistrationListMapper;
import project.carPooling.driver.mapper.RegistrationMapper;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.repository.DriverReviewRepository;
import project.carPooling.driver.repository.DrivingRepository;
import project.carPooling.driver.repository.MybatisDriverInfoRepository;
import project.carPooling.driver.repository.MybatisDriverReviewRepository;
import project.carPooling.driver.repository.MybatisDrivingRepository;
import project.carPooling.driver.repository.MybatisRegistrationListRepository;
import project.carPooling.driver.repository.MybatisRegistrationRepository;
import project.carPooling.driver.repository.RegistrationListRepository;
import project.carPooling.driver.repository.RegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class DriverAppConfig {
	
	private final DriverInfoMapper driverMapper;
	private final RegistrationMapper registrationMapper;
	private final RegistrationListMapper registrationListMapper;
	private final DriverReviewMapper driverReviewMapper;
	private final DrivingMapper drivingMapper;

	@Bean
	public DriverInfoRepository driverRepository() {
		return new MybatisDriverInfoRepository(driverMapper);
	}
	
	@Bean
	public RegistrationRepository registrationRepository() {
		return new MybatisRegistrationRepository(registrationMapper);
	}
	
	@Bean
	public RegistrationListRepository registrationListRepository() {
		return new MybatisRegistrationListRepository(registrationListMapper);
	}
	
	@Bean
	public DriverReviewRepository driverReviewRepository() {
		return new MybatisDriverReviewRepository(driverReviewMapper);
	}

	@Bean
	public DrivingRepository drivingRepository() {
		return new MybatisDrivingRepository(drivingMapper);
	}
}

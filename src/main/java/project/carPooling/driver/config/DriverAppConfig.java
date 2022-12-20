package project.carPooling.driver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.DriverInfoMapper;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.repository.MybatisDriverInfoRepository;

@Configuration
@RequiredArgsConstructor
public class DriverAppConfig {
	
	private final DriverInfoMapper driverMapper;

	@Bean
	public DriverInfoRepository driverRepository() {
		return new MybatisDriverInfoRepository(driverMapper);
	}
}

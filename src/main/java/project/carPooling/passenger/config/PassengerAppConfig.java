package project.carPooling.passenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.mapper.PassengerInfoMapper;

@Configuration
@RequiredArgsConstructor
public class PassengerAppConfig {
	
	private final PassengerInfoMapper psgMapper;
	
	@Bean
	public PassengerInfoRepository() {
		return new MybatisPassengerInfoRepository(psgMapper);
	}

}

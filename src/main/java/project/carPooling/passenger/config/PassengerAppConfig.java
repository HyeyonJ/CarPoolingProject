package project.carPooling.passenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.mapper.PassengerInfoMapper;
import project.carPooling.passenger.repository.MybatisPassengerInfoRepository;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Configuration
@RequiredArgsConstructor
public class PassengerAppConfig {
	
	private final PassengerInfoMapper psgMapper;
	
	@Bean
	public PassengerInfoRepository passengerRepository() {
		return new MybatisPassengerInfoRepository(psgMapper);
	}

}

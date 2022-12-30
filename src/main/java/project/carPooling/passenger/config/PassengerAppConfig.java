package project.carPooling.passenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.mapper.PassengerInfoMapper;
import project.carPooling.passenger.mapper.ReservationListMapper;
import project.carPooling.passenger.mapper.SearchCarpoolMapper;
import project.carPooling.passenger.repository.MybatisPassengerInfoRepository;
import project.carPooling.passenger.repository.MybatisReservationListRepository;
import project.carPooling.passenger.repository.MybatisSearchCarpoolRepository;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.repository.ReservationListRepository;
import project.carPooling.passenger.repository.SearchCarpoolRepository;

@Configuration
@RequiredArgsConstructor
public class PassengerAppConfig {
	
	private final PassengerInfoMapper passengerMapper;
	private final SearchCarpoolMapper searchCarpoolMapper;
	private final ReservationListMapper reservationListMapper;
	
	@Bean
	public PassengerInfoRepository passengerInfoRepository() {
		return new MybatisPassengerInfoRepository(passengerMapper);
	}
	
	@Bean
	public SearchCarpoolRepository searchCarpoolRepository() {
		return new MybatisSearchCarpoolRepository(searchCarpoolMapper);
	}

	@Bean
	public ReservationListRepository reservationListRepository() {
		return new MybatisReservationListRepository(reservationListMapper);
	}

}

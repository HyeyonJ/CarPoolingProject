package project.carPooling.passenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.mapper.PassengerInfoMapper;
import project.carPooling.passenger.mapper.PassengerReviewMapper;
import project.carPooling.passenger.mapper.ReservationListMapper;
import project.carPooling.passenger.mapper.ReservationMapper;
import project.carPooling.passenger.mapper.RidingMapper;
import project.carPooling.passenger.repository.MybatisPassengerInfoRepository;
import project.carPooling.passenger.repository.MybatisPassengerReviewRepository;
import project.carPooling.passenger.repository.MybatisReservationListRepository;
import project.carPooling.passenger.repository.MybatisReservationRepository;
import project.carPooling.passenger.repository.MybatisRidingRepository;
import project.carPooling.passenger.repository.PassengerInfoRepository;
import project.carPooling.passenger.repository.PassengerReviewRepository;
import project.carPooling.passenger.repository.ReservationListRepository;
import project.carPooling.passenger.repository.ReservationRepository;
import project.carPooling.passenger.repository.RidingRepository;

@Configuration
@RequiredArgsConstructor
public class PassengerAppConfig<paymentMapper> {
	
	private final PassengerInfoMapper passengerMapper;
	private final ReservationMapper reservationMapper;
	private final ReservationListMapper reservationListMapper;
	private final PassengerReviewMapper passengerReviewMapper;
	private final RidingMapper ridingMapper;
	
	@Bean
	public PassengerInfoRepository passengerInfoRepository() {
		return new MybatisPassengerInfoRepository(passengerMapper);
	}
	
	@Bean
	public ReservationRepository reservationRepository() {
		return new MybatisReservationRepository(reservationMapper);
	}

	@Bean
	public ReservationListRepository reservationListRepository() {
		return new MybatisReservationListRepository(reservationListMapper);
	}
	
	@Bean
	public PassengerReviewRepository passengerReviewRepository() {
		return new MybatisPassengerReviewRepository(passengerReviewMapper);
	}
	
	@Bean
	public RidingRepository ridingRepository() {
		return new MybatisRidingRepository(ridingMapper);
	}
	
}

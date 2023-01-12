package project.carPooling.global.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.global.payment.mapper.PaymentMapper;
import project.carPooling.global.payment.repository.MybatisPaymentRepository;
import project.carPooling.global.payment.repository.PaymentRepository;

@Configuration
@RequiredArgsConstructor
public class paymentAppConfig {

	private final PaymentMapper paymentMapper;
	
	@Bean
	public PaymentRepository passengerPaymentRepository() {
		return new MybatisPaymentRepository(paymentMapper);
	}
}

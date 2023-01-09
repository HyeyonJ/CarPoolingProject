package project.carPooling.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import project.carPooling.global.filter.LoginFilter;

@Configuration
public class FilterConfig {

//	@Bean
	public FilterRegistrationBean<Filter> driverLoginFilter() {		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();		
		filterRegistrationBean.setFilter(new LoginFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");		
		return filterRegistrationBean;
	}
	
}

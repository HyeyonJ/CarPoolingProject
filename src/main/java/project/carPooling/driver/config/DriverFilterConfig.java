package project.carPooling.driver.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import project.carPooling.driver.filter.DriverLoginFilter;

@Configuration	//프로젝트 내에서 필요한 설정을 별도로 주입시켜야할 때 사용하는 어노테이션
public class DriverFilterConfig {
	
//	@Bean
	public FilterRegistrationBean<Filter> driverLoginFilter() {		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();		
		filterRegistrationBean.setFilter(new DriverLoginFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*");		
		return filterRegistrationBean;
	}

}

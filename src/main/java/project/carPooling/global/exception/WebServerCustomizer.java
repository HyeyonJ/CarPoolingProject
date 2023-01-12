package project.carPooling.global.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		// 요청할 에러코드에대한 에러페이지의 경로		
		ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage error400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		ErrorPage errorRE = new ErrorPage(RuntimeException.class, "/error/500");
		//RuntiemException도 INTERNAL_SERVER_ERROR에 포함되므로 같은 경로에서 보여주기로 함
		
		factory.addErrorPages(error404, error400, error500, errorRE);
	}


}

package edu.du.airline_project.config;

import edu.du.airline_project.handler.AuthInterceptor;
import edu.du.airline_project.handler.AuthInterceptorForManager;
import edu.du.airline_project.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Autowired
	private AuthInterceptorForManager authInterceptorForManager;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(
						"https://online-payment.kakaopay.com",
						"https://t1.kakaocdn.net",
						"https://developers.kakao.com",
						"https://kapi.kakao.com",
						"https://cdn.online-payment.kakaopay.com",
						"https://payment.kakao.com",
						"http://localhost:8082",
						"https://mockup-pg-web.kakao.com",
						"https://pg-web.kakao.com",
						"https://online-payment.kakao.com"
				)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
				.allowedHeaders(
						"Authorization",
						"Content-Type",
						"X-Requested-With",
						"accept",
						"Origin",
						"Access-Control-Request-Method",
						"Access-Control-Request-Headers",
						"Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials"
				)
				.exposedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
	}
	 // 학원 이미지 경로
	  @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/uploadImage/**")
	  .addResourceLocations("file:///C:\\upload/");
	  
	  	// 맥북 이미지 경로인데 남겨두면 도움이 될지도?? -정다운-
	/*
	 * registry.addResourceHandler("/uploadImage/**")
	 * .addResourceLocations("file:/Users/minjoo/Desktop/images/");
	 */
	  
	  }
	
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }
	  
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns(Define.PATHS);
		registry.addInterceptor(authInterceptorForManager).addPathPatterns(Define.MANAGER_PATHS);
	}
	
}

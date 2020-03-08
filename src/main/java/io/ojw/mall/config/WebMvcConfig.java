package io.ojw.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.ojw.mall.interceptor.JwtInterceptor;
import io.ojw.mall.user.jwt.JwtService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private JwtService jwtService;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor(jwtService))
				.addPathPatterns("/**/jwt-auth/**");
	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://35.222.169.33:9000")
                .exposedHeaders("jwt-token")	//make client read header("jwt-token")
                ;

    }
}

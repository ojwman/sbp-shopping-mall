package io.ojw.mall;

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
				.addPathPatterns("/user/auth/*/*");
	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");

    }
}

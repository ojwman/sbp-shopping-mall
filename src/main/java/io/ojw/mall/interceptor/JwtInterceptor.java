package io.ojw.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.ojw.mall.user.jwt.JwtService;
import io.ojw.mall.user.jwt.UnauthorizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.interceptor.JwtInterceptor");
	
	private static final String TOKEN = "token";

	private JwtService jwtService;
	
	public JwtInterceptor(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(TOKEN);
		
		logger.debug("JwtInterceptor > preHandle > token: " + token);
		
		if(token != null && jwtService.checkToken(token)){
			return true;
		}else{
			throw new UnauthorizedException();
		}
	}
}

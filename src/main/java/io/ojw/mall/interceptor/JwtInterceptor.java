package io.ojw.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.ojw.mall.user.jwt.JwtService;
import io.ojw.mall.user.jwt.UnauthorizedException;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	private static final String TOKEN = "token";

	private JwtService jwtService;
	
	public JwtInterceptor(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(TOKEN);
		
		System.out.println("JwtInterceptor > preHandle > token: " + token);
		
		if(token != null && jwtService.checkToken(token)){
			return true;
		}else{
			throw new UnauthorizedException();
		}
	}
}

package io.ojw.mall.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ojw.mall.user.domain.User;
import io.ojw.mall.user.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;
	
	public User checkUser(String id, String password) throws Exception {
		User user = mapper.checkUser(id, password);
		
		return user;
	}
	
	public User getMyInfo(String id) throws Exception {
		User user = mapper.getMyInfo(id);
		
		return user;
	}
	
	public User getMyInfo2(Map<?, ?> mapParam) throws Exception {
		User user = mapper.getMyInfo2(mapParam);
		
		return user;
	}
}

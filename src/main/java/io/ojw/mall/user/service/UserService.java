package io.ojw.mall.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ojw.mall.user.domain.User;
import io.ojw.mall.user.domain.UserSignUp;
import io.ojw.mall.user.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User checkUser(String id, String password) {
		User user = userMapper.checkUser(id, password);
		
		return user;
	}
	
	public User getMyInfo(String id) {
		User user = userMapper.getMyInfo(id);
		
		return user;
	}
	
	public User getMyInfo2(Map<?, ?> mapParam) {
		User user = userMapper.getMyInfo2(mapParam);
		
		return user;
	}
	
	public int insertUser(UserSignUp userSignUp) {
		return userMapper.insertUser(userSignUp);
	}
	
}

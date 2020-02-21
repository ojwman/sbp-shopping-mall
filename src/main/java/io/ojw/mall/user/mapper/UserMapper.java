package io.ojw.mall.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import io.ojw.mall.user.domain.User;
import io.ojw.mall.user.domain.UserSignUp;

public interface UserMapper {
	public User checkUser(@Param("id") String id, @Param("password") String password);
	public User getMyInfo(@Param("id") String id);
	public User getMyInfo2(Map<?, ?> mapParam);

	public Integer existsId(@Param("id") String id);
	public int insertUser(UserSignUp userSignUp);
}

package io.ojw.mall.user.mapper;

import org.apache.ibatis.annotations.Param;

import io.ojw.mall.user.domain.User;

public interface UserMapper {
	public User checkUser(@Param("id") String id, @Param("password") String password) throws Exception;
	public User getMyInfo(@Param("id") String id) throws Exception;
}

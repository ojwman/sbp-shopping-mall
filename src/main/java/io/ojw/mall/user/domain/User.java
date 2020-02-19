package io.ojw.mall.user.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"password", "phone"})
public class User {
	private String id;
	private String name;
	private String password;
	private String auth;
	private String phone;
}

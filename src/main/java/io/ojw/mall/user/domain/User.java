package io.ojw.mall.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = {"password", "phone"})
public class User {
	private String id;
	private String name;
	private String password;
	private String auth;
	private String phone;
}

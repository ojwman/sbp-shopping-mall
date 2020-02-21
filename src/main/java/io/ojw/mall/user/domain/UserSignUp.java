package io.ojw.mall.user.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSignUp {
	@NotBlank
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@NotBlank
	private String passwordCheck;
	private String phone;
}

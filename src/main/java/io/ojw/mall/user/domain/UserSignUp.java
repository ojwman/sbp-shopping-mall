package io.ojw.mall.user.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSignUp {
	private String id;
	private String name;
	@NotBlank @NotEmpty
	private String password;
	@NotBlank @NotEmpty
	private String passwordCheck;
	private String phone;
}

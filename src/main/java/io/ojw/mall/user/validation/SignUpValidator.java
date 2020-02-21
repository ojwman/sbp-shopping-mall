package io.ojw.mall.user.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.ojw.mall.user.domain.UserSignUp;

@Component
public class SignUpValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return UserSignUp.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserSignUp userSignUp = (UserSignUp) target;
		
		if(! StringUtils.equals(userSignUp.getPassword(), userSignUp.getPasswordCheck())){
			errors.rejectValue("password", "password and password(check) should be same");
		}
	}
}
package io.ojw.mall.user.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.ojw.mall.user.domain.UserSignUp;
import io.ojw.mall.user.mapper.UserMapper;

@Component
public class SignUpValidator implements Validator {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.user.validation.SignUpValidator");
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserSignUp.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserSignUp userSignUp = (UserSignUp) target;
		
		String id = userSignUp.getId();
		Integer result = userMapper.existsId(id);
		logger.debug("existsId result: " + result);
		if (result != null) {
			errors.rejectValue("id", "id aleady used");
		}
		
		if(! StringUtils.equals(userSignUp.getPassword(), userSignUp.getPasswordCheck())){
			errors.rejectValue("password", "password and password(check) should be same");
		}
	}
}
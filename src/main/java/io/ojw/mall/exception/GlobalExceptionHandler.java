package io.ojw.mall.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.ojw.mall.user.jwt.UnauthorizedException;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.exception.GlobalExceptionHandler");
	
	@ExceptionHandler(value = UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(Exception e) {
    	return e.getMessage();
    }
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
	public ResponseEntity<?> handleException(HttpServletRequest request, Exception e) {
    	logger.error("===start handleException");
    	logger.error("URI: " + request.getRequestURI());
    	logger.error("Method: " + request.getMethod());
//        	logger.error("Body: " + request.getReader().readLine());
//        	logger.error("Body: " + httpEntity.getBody());
    	logger.error("Error Message: " + e.getMessage());
    	logger.error("Error Stack: " + ExceptionUtils.getStackTrace(e));
    	logger.error("===end handleException");
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}  
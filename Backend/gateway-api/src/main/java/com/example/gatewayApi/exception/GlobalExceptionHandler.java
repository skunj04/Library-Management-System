package com.example.gatewayApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value = UnAuthorizedUserException.class)
	public ResponseEntity<String> BookAlreadyExistsException(UnAuthorizedUserException bae){
	        return new ResponseEntity<String>("Bad Credentials", HttpStatus.UNAUTHORIZED);
	   }
}

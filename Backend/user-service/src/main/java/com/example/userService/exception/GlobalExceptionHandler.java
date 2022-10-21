package com.example.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<String> UserAlreadyExistsException(UserAlreadyExistsException uae){
	        return new ResponseEntity<String>("User Already exists!", HttpStatus.CONFLICT);
	   }
	
	@ExceptionHandler(value = NoUserExistsException.class)
	public ResponseEntity<String> NoUserExistsException(NoUserExistsException nue){
	        return new ResponseEntity<String>("No such Person Exists!", HttpStatus.CONFLICT);
	   }

}

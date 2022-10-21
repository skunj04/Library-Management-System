package com.example.bookService.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<String> NotFoundException(NotFoundException nbe){
	        return new ResponseEntity<String>("No issued Book Exists!", HttpStatus.CONFLICT);
	   }
}

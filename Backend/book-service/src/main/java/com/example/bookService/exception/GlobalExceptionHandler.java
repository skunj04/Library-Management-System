package com.example.bookService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value = BookAlreadyExistsException.class)
	public ResponseEntity<String> BookAlreadyExistsException(BookAlreadyExistsException bae){
	        return new ResponseEntity<String>("Book Already exists!", HttpStatus.CONFLICT);
	   }
	
	@ExceptionHandler(value = NoBookExistsException.class)
	public ResponseEntity<String> NoBookExistsException(NoBookExistsException nbe){
	        return new ResponseEntity<String>("No such Book Exists!", HttpStatus.CONFLICT);
	   }
}

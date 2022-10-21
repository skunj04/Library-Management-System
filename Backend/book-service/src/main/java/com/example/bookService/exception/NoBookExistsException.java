package com.example.bookService.exception;

public class NoBookExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoBookExistsException() {
		super();
	}

	public NoBookExistsException(String message) {
		super(message);
	}


}

package com.example.bookService.exception;

public class BookAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException() {
		super();
	}

	public BookAlreadyExistsException(String message) {
		super(message);
	}

}

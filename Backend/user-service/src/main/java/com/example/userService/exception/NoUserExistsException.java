package com.example.userService.exception;

public class NoUserExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoUserExistsException() {
		super();
	}

	public NoUserExistsException(String message) {
		super(message);
	}
}

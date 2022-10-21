package com.example.gatewayApi.exception;

public class UnAuthorizedUserException extends Exception{
	private static final long serialVersionUID = 1L;

	public UnAuthorizedUserException() {
		super();
	}

	public UnAuthorizedUserException(String message) {
		super(message);
	}

}

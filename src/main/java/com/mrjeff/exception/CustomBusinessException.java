package com.mrjeff.exception;

import org.springframework.http.HttpStatus;

public class CustomBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 7856391465725017697L;
	private final HttpStatus status;

	public CustomBusinessException(String message, HttpStatus status) {
	        super(message);
	        this.status = status;
	    }

	public HttpStatus getStatus() {
		return status;
	}
}

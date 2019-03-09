package com.mrjeff.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomBusinessExceptionControllerAdvice {

	@ExceptionHandler(CustomBusinessException.class)
	public ResponseEntity<String> businessException(CustomBusinessException customBusinessException) {
		return ResponseEntity.status(customBusinessException.getStatus())
				.header("Error", customBusinessException.getLocalizedMessage() + "\"")
				.body(customBusinessException.getMessage());
	}
}

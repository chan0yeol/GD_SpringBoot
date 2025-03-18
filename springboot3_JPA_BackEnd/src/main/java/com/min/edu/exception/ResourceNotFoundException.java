package com.min.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4512640691148970123L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}

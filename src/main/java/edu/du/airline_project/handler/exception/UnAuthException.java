package edu.du.airline_project.handler.exception;

import org.springframework.http.HttpStatus;

public class UnAuthException extends RuntimeException{
	
	private HttpStatus status;

	public UnAuthException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
}

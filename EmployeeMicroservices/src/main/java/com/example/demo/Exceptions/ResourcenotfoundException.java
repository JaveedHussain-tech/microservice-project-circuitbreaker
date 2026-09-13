package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class ResourcenotfoundException extends RuntimeException {
	
	private String message;
	private HttpStatus status;
	public ResourcenotfoundException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.status = HttpStatus.NOT_FOUND;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	

}

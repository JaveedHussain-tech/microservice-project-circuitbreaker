package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class FeignClientException extends RuntimeException{
	
	private HttpStatus status;
	
	public FeignClientException(String message,HttpStatus status) {
		super(message);
		this.status=status;
		
	}
	
	public FeignClientException(String message) {
		super(message);
		this.status=HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}

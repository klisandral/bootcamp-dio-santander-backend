package com.bytebank.bootcamp.exceptions;

public class ExceptionResponse {
	
	private String message;
	
	public ExceptionResponse (String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}

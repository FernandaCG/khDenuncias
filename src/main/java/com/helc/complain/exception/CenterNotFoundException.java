package com.helc.complain.exception;

public class CenterNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CenterNotFoundException() {
		super();
	}
	public CenterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public CenterNotFoundException(String message) {
		super(message);
	}

}

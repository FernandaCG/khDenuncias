package com.helc.complain.exception;

public class PhotoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhotoNotFoundException() {

	}

	public PhotoNotFoundException(String message) {
		super(message);

	}

	public PhotoNotFoundException(Throwable cause) {
		super(cause);

	}

	public PhotoNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public PhotoNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}

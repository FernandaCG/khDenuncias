package com.helc.complain.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.Nullable;

public class ComplainsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComplainsNotFoundException() {
		super();
	}

	public ComplainsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ComplainsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComplainsNotFoundException(String message) {
		super(message);
	}

	public ComplainsNotFoundException(Throwable cause) {
		super(cause);
	}

	public Throwable getMostSpecificCause() {
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}

	@Nullable
	public Throwable getRootCause() {
		return NestedExceptionUtils.getRootCause(this);
	}

}

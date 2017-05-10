package com.vp.plugin.exceptions;

public class ValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6559854679933777931L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}

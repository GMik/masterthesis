package com.vp.plugin.utils.validation;

public class ValidationResult {
	protected boolean result;

	protected String message;

	public ValidationResult() {

	}

	public ValidationResult(boolean result, String message) {
		this.result = result;
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

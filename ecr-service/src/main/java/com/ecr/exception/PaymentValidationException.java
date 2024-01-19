package com.ecr.exception;

public class PaymentValidationException extends Exception {
	
	private static final long serialVersionUID = 8331724872991321980L;

	private String errorMessage;
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public PaymentValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PaymentValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentValidationException(String message, String errorCode) {
		this.errorMessage = message;
		this.errorCode = errorCode;
	}

	public PaymentValidationException(Throwable cause) {
		super(cause);
	}

	
	
}

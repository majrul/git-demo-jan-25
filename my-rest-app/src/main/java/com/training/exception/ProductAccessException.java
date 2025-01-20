package com.training.exception;

public class ProductAccessException extends RuntimeException {

	public ProductAccessException() {
	}

	public ProductAccessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductAccessException(String message) {
		super(message);
	}

	public ProductAccessException(Throwable cause) {
		super(cause);
	}

	
}

package com.barclays.demo.domain.api;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 7006109831119967024L;

	private ErrorResponse errorResponse;

	public ServiceException() {

	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception exception) {
		super(exception);
	}

	public ServiceException(ErrorResponse errorResponse, String message) {
		super(message);
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}

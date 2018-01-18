package com.martin.web.rest.server.service.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CommonRestException extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	public CommonRestException() {
		super();
	}

	public CommonRestException(int status) {
		super(status);
	}

	public CommonRestException(Response response) {
		super(response);
	}

	public CommonRestException(Status status) {
		super(status);
	}

	public CommonRestException(String message, int status) {
		super(message, status);
	}

	public CommonRestException(String message, Response response) {
		super(message, response);
	}

	public CommonRestException(String message, Status status) {
		super(message, status);
	}

	public CommonRestException(String message, Throwable cause, int status) {
		super(message, cause, status);
	}

	public CommonRestException(String message, Throwable cause, Response response) {
		super(message, cause, response);
	}

	public CommonRestException(String message, Throwable cause, Status status) throws IllegalArgumentException {
		super(message, cause, status);
	}

	public CommonRestException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonRestException(String message) {
		super(message);
	}

	public CommonRestException(Throwable cause, int status) {
		super(cause, status);
	}

	public CommonRestException(Throwable cause, Response response) {
		super(cause, response);
	}

	public CommonRestException(Throwable cause, Status status) throws IllegalArgumentException {
		super(cause, status);
	}

	public CommonRestException(Throwable cause) {
		super(cause);
	}
}

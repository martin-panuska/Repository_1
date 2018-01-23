package com.martin.web.rest.server.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class EntityNotFoundException extends CommonRestException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(int status) {
		super(status);
	}

	public EntityNotFoundException(Response response) {
		super(response);
	}

	public EntityNotFoundException(Status status) {
		super(status);
	}

	public EntityNotFoundException(String message, int status) {
		super(message, status);
	}

	public EntityNotFoundException(String message, Response response) {
		super(message, response);
	}

	public EntityNotFoundException(String message, Status status) {
		super(message, status);
	}

	public EntityNotFoundException(String message, Throwable cause, int status) {
		super(message, cause, status);
	}

	public EntityNotFoundException(String message, Throwable cause, Response response) {
		super(message, cause, response);
	}

	public EntityNotFoundException(String message, Throwable cause, Status status) throws IllegalArgumentException {
		super(message, cause, status);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause, int status) {
		super(cause, status);
	}

	public EntityNotFoundException(Throwable cause, Response response) {
		super(cause, response);
	}

	public EntityNotFoundException(Throwable cause, Status status) throws IllegalArgumentException {
		super(cause, status);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

}

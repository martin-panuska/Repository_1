package com.martin.web.rest.server.service.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/** Exception mapper to handle exceptions not extending CommonRestException */
/**
 * The class below is annotated with @Provider, this declares that the class is
 * of interest to the JAX-RS runtime. Such a class may be added to the set of
 * classes of the Application instance that is configured. When an application
 * throws an EntityNotFoundException the toResponse method of the
 * EntityNotFoundMapper instance will be invoked.
 */
@Provider
public class RootExceptionMapper implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable exception) {
		return Response.status(Status.NOT_FOUND).entity("Exception handled by " + this.getClass().getName() + ". Message: " + exception.getMessage()).type("text/plain").build();
	}
}
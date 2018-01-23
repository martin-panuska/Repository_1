package com.martin.web.rest.server.service.exceptionmapper.extended;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import com.martin.model.Person;
import com.martin.web.rest.server.service.exception.CommonRestException;

/** Exception mapper to handle exceptions extending CommonRestException */
/**
 * The class below is annotated with @Provider, this declares that the class is
 * of interest to the JAX-RS runtime. Such a class may be added to the set of
 * classes of the Application instance that is configured. When an application
 * throws an EntityNotFoundException the toResponse method of the
 * EntityNotFoundMapper instance will be invoked.
 */
@Provider
public class ApplicationExceptionMapper implements ExtendedExceptionMapper<CommonRestException> {

	@Override
	public Response toResponse(CommonRestException exception) {
		Person p = new Person();
		p.setFirstName("xxx");
		return Response.status(Status.NOT_FOUND).entity("Exception handled by " + this.getClass().getName() + ". Message: " + exception.getMessage()).type("text/plain").build();
		//return Response.status(Status.NOT_FOUND).entity(p).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@Override
	public boolean isMappable(CommonRestException exception) {
		return true;
	}

}

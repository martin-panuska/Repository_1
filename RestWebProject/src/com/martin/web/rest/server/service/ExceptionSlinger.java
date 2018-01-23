package com.martin.web.rest.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.martin.web.rest.server.service.exception.CommonRestException;
import com.martin.web.rest.server.service.exception.EntityNotFoundException;

@Path("/exception")
public class ExceptionSlinger {
	
	//http://localhost:8080/RestWebProject/rest/exception/root
	@Path("/root")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String throwExceptionForRootMapper() {
		throw new RuntimeException("Exception to be handled by RootExceptionMapper");
	}
	
	//http://localhost:8080/RestWebProject/rest/exception/app1
	@Path("/app1")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String throwCommonAppException1() {
		throw new CommonRestException("Exception to be handled by ApplicationExceptionMapper");
	}
	
	@Path("/app2")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String throwCommonAppException2() {
		throw new EntityNotFoundException("Exception to be handled by ApplicationExceptionMapper");
	}
}

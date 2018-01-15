package com.martin.web.rest.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pathExamples/{firstName: [a-zA-Z][a-zA-Z_0-9]*}")
public class PathExamples {
	@PathParam("firstName")
	private String firstName;
	
	public PathExamples() {
		super();
	}
	
	//http://localhost:8080/RestWebProject/rest/pathExamples/Matko
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDefaultResponse() {
		return "This is default response for HTML request";
	}
	
	//http://localhost:8080/RestWebProject/rest/pathExamples/Matko
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello " + this.firstName;
	}
	
	//http://localhost:8080/RestWebProject/rest/pathExamples/Matko/Pancuska
	@GET
	@Path("/{lastName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloToFirstAndLastName(@PathParam("lastName") String lastName) {
		return "Hello " + this.firstName + " " + lastName;
	}
	
	//http://localhost:8080/RestWebProject/rest/pathExamples/Matko/Pancuska/age/2
	@GET
	@Path("/{lastName}/age/{age: \\d+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloToFirstAndLastNameWithAge(@PathParam("lastName") String lastName, @PathParam("age") String age) {
		return "Hello " + this.firstName + " " + lastName + ", age: " + age;
	}
}

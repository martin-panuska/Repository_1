package com.martin.web.rest.server.service;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.martin.model.Address;

@Path("/query")
public class QueryParamExamples {
	@DefaultValue("Matko")
	@QueryParam("firstName")
	private String firstName;
	
	@DefaultValue("Pancuska")
	@QueryParam("lastName")
	private String lastName;
	
	@HeaderParam("Accept")
	private String acceptsHeader;
	
	public QueryParamExamples() {
		super();
	}
	
	//http://localhost:8080/RestWebProject/rest/query/example_1?firstName=Peter&lastName=Steurer
	@GET
	@Path("/example_1")
	@Produces({MediaType.TEXT_PLAIN})
	public String returnBack() {
		return this.firstName + " " + this.lastName;
	}
	
	//http://localhost:8080/RestWebProject/rest/query/example_2/Martin?lastName=Panuskovie
	@GET
	@Path("/example_2/{firstName}")
	public String get(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    MultivaluedMap<String, String> pathParams = ui.getPathParameters();
	    
	    return "First name: " + pathParams.getFirst("firstName") + ", last name: " + queryParams.getFirst("lastName");
	}
	
	//http://localhost:8080/RestWebProject/rest/query/example_3/Slovakia/Zilina?street=Bernolakova
	@GET
	@Path("/example_3/{state}/{city}")
	public String createObjectFromUrl(@BeanParam Address a) {
		return "";
	}
}

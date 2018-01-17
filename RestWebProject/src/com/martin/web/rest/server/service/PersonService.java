package com.martin.web.rest.server.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.martin.model.Person;
import com.martin.web.rest.server.storage.ObjectStorage;

@Path("/person")
public class PersonService {
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;
	
    //http://localhost:8080/RestWebProject/rest/person/test
    @GET
    @Path("/test")
    @Produces({MediaType.TEXT_PLAIN})
    public String test() {
    	return "Test OK";
    }
    
    //http://localhost:8080/RestWebProject/rest/person/1
    @GET
    @Path("/{personID}")
    @Produces({MediaType.APPLICATION_JSON})
	public Person getPerson(@PathParam("personID") long id) {
		if(id < 0) {
			throw new WebApplicationException("Please provide valid ID", Status.BAD_REQUEST);
		}
		
		Person p = (Person) ObjectStorage.getObject(id);
		
		if(p == null) {
			throw new WebApplicationException("Person not found", Status.BAD_REQUEST);
		}
		return p;
	}
    
    //http://localhost:8080/RestWebProject/rest/person/get/1
    @GET
    @Path("/get/{personID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonAnotherApproach(@PathParam("personID") long id) {
        if(id < 0) {
            return Response.status(400).entity("Please provide valid person ID").build();
        }
        
        Person p = (Person) ObjectStorage.getObject(id);
		
		if(p == null) {
			return Response.status(Status.NO_CONTENT).entity("Person not found").build();
		}
        return Response.ok().entity(p).build();
    }
    
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Person p) {
    	Long objectID = ObjectStorage.storeObject(p);
    	p.setId(objectID);
    	try {
			return Response.created(new URI(uriInfo.getBaseUriBuilder().path("/person/get/" + objectID).toString())).entity(objectID).build();
		} catch (URISyntaxException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
    }
}

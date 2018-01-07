package com.martin.web.rest.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.martin.model.Person;

@Path("/person")
public class PersonHandler {
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Person getPerson() {
		Person p = new Person();
		p.setFirstName("Martin");
		return p;
	}
}

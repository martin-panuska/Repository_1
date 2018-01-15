package com.martin.web.rest.server.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.martin.model.Person;
import com.martin.model.constant.Gender;

@Path("/person")
public class JerseyProducesExamples {
	//http://localhost:8080/RestWebProject/rest/person/xml
	@Path("/xml")
	@GET
	@Produces({MediaType.APPLICATION_XML})
	public Person getXmlPerson() {
		Person p = new Person();
		p.setFirstName("Martin");
		p.setGender(Gender.MALE);
		return p;
	}
	
	//http://localhost:8080/RestWebProject/rest/person/json
	@Path("/json")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Person getJsonPerson() {
		Person p = new Person();
		p.setFirstName("Martin");
		p.setGender(Gender.MALE);
		return p;
	}
	
	//http://localhost:8080/RestWebProject/rest/person/xml_or_json
	@Path("/xml_or_json")
	@GET
	/** qs defines preference of server.*/
	@Produces({MediaType.APPLICATION_JSON + "; qs=0.8", MediaType.APPLICATION_XML + "; qs=0.9"})
	@Consumes("text/plain")
	public Person getXmlOrJsonPerson() {
		Person p = new Person();
		p.setFirstName("Martin");
		p.setGender(Gender.MALE);
		return p;
	}
}

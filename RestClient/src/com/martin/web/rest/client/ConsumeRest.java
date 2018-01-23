package com.martin.web.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import com.martin.model.Person;

public class ConsumeRest {
	private static WebTarget getRootWebTarget() {
		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.register(MyClientResponseFilter.class);
//		clientConfig.register(new AnotherClientFilter());
		 
		Client client = ClientBuilder.newClient(clientConfig);
//		client.register(ThirdClientFilter.class);
		 
		WebTarget webTarget = client.target("http://localhost:8080/RestWebProject/rest/");
		return webTarget;
	}
	
	private static WebTarget createWebTargetForPerson() {
		return getRootWebTarget().path("person/");
	}
	
	private static Person invokeGet(WebTarget resourceTarget, String... acceptedMediaTypes) throws Exception {
		Invocation.Builder invocationBuilder = resourceTarget.request(acceptedMediaTypes);
		invocationBuilder.header("some-header", "true");
		Response response = invocationBuilder.get();
		System.out.println("Get person response status: " + response.getStatus() + " - " + response.getStatusInfo());
		
		if(response.getStatusInfo().getStatusCode() == Status.OK.getStatusCode()) {
			return response.readEntity(Person.class);
		}
		
		throw new Exception("Person not loaded");
	}
	
	private static String invokePut(Person p, WebTarget resourceTarget, String... acceptedMediaTypes) throws Exception {
		Invocation.Builder invocationBuilder = resourceTarget.request(acceptedMediaTypes);
		invocationBuilder.header("some-header", "true");
		Response response = invocationBuilder.put(Entity.entity(p, MediaType.APPLICATION_JSON), Response.class);
		System.out.println("Create person response status: " + response.getStatus() + " - " + response.getStatusInfo());
		
		if(response.getStatusInfo().getStatusCode() == Status.CREATED.getStatusCode()) {
			System.out.println("Created and available under " + response.getHeaderString("Location"));
			return response.readEntity(String.class);
		}
		
		throw new Exception("Person not created");
	}
	
	public static String createPerson() throws Exception {
		Person p = new Person();
		p.setFirstName("A");
		p.setLastName("B");
		
		return invokePut(p, createWebTargetForPerson().path("create"), MediaType.APPLICATION_JSON);
	}
	
	public static Person readPerson(Long personID) throws Exception {
		return invokeGet(createWebTargetForPerson().path("get/" + personID), MediaType.APPLICATION_JSON);
	}
	
	public static void main(String[] args) {
		try {
			String personID = createPerson();
			System.out.println("New person ID: " + personID);
			Person p = readPerson(new Long(personID));
			System.out.println("Person ID " + p.getId());
			
			Person p2 = invokeGet(ClientBuilder.newClient(new ClientConfig()).target("http://localhost:8080/RestWebProject/rest/person/get/" + p.getId()), MediaType.APPLICATION_JSON);
			System.out.println("Person 2 ID " + p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

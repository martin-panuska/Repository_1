package com.martin.web.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class ConsumeRest {
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/RestWebProject").build();
	}

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		Response res = target.path("rest").path("helloRest").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
		String response = res.toString();

		String plainAnswer = target.path("rest").path("helloRest").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String xmlAnswer = target.path("rest").path("helloRest").request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path("rest").path("helloRest").request().accept(MediaType.TEXT_HTML).get(String.class);

		System.out.println(response);
		System.out.println(plainAnswer);
		System.out.println(xmlAnswer);
		System.out.println(htmlAnswer);
	}
}

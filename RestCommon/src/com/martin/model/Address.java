package com.martin.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class Address {
	@PathParam("state")
	@DefaultValue("N/A")
	private String state;
	
	@PathParam("city")
	@DefaultValue("N/A")
	private String city;
	
	@QueryParam("street")
	@DefaultValue("N/A")
	private String street;
	
	@HeaderParam("Accept")
	private String accept;
	
	public Address() {
		super();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}

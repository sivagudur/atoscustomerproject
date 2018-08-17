package com.atos.demo.rest.api.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



/**
 * A class for JPA Hibernate.
 * @author Siva Gudur
 *
 */
public class Customer {
	
	
	private int id;
	
	@NotNull
	@NotEmpty
	private String firstName;
	
	@NotNull
	@NotEmpty
	private String surName;
	
	
	public Customer() {
	}

	public Customer(String firstName, String surName) {
		this.firstName = firstName;
		this.surName = surName;
		
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getSurName() {
		return surName;
	}

	public void setSurName(String name) {
		this.surName = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("firstName=%s, surName=%s", this.firstName, this.surName);
	}

	
}


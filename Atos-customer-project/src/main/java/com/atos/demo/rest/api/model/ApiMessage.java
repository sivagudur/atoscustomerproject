package com.atos.demo.rest.api.model;


/**
 * This class has the ApiMessage for REST.
 * 
 * @author Siva Gudur
 *
 */
public class ApiMessage {
private String message;
	
	public ApiMessage(String msg){
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

}

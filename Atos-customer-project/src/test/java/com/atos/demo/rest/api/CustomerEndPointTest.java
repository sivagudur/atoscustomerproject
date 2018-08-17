package com.atos.demo.rest.api;


import org.junit.Test;
import com.atos.demo.rest.api.model.Customer;
import static com.jayway.restassured.RestAssured.given;



public class CustomerEndPointTest extends FunctionalTest{
	
	@Test
    public void basicCustomerGetTest() {		
		given().auth().preemptive().basic("user", "pass").when().get("/customer/1").then().statusCode(200);		
    }
	
	@Test
    public void addingCustomerPostTest() {
        Customer customerObj = new Customer("Amish", "Gudur");

        given()
        .auth().preemptive().basic("user", "pass").contentType("application/json")
        .body(customerObj)
        .when().post("/customer").then()
        .statusCode(200);
    }
	
	
}

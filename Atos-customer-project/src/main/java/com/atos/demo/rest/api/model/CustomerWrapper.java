package com.atos.demo.rest.api.model;

import java.util.List;

import com.atos.demo.rest.api.model.Customer;
import com.atos.demo.rest.api.model.CustomerWrapper;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author Siva Gudur
 *
 */
public class CustomerWrapper {
	
	private List<Customer> customer;
	
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	
	public List<Customer> getCustomers(){
		return customer;
	}
	
	public static CustomerWrapper createNew(List<Customer> customersList){
		CustomerWrapper pw = new CustomerWrapper();
		pw.setCustomer(customersList);
		return pw;
	}
}


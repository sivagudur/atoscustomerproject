package com.atos.demo.rest.api.service;

import java.util.List;

import com.atos.demo.rest.api.model.Customer;

/**
 * This class is interface for CustomerService.
 * 
 * @author Siva Gudur
 *
 */
public interface ICustomerService {
	public List<Customer> getCustomer();
	
	public Customer getCustomer(int id);
	public int addCustomer(Customer customern);
	public void updateCustomer(int id, Customer price);
	public void deleteCustomer(int id);
}

package com.atos.demo.rest.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.atos.demo.rest.api.model.Customer;

@Service("customerService")
/**
 * This class Serrvice layer which performs various services of Customer.
 * 
 * @author Siva Gudur
 *
 */
public class CustomerService implements ICustomerService {

	private Map<Integer, Customer> customer = new HashMap<Integer, Customer>();
	private AtomicInteger idGen = new AtomicInteger();

	public CustomerService() {
		//Adding dummy data for 2 customers
		addCustomer(new Customer("Siva", "Gudur"));
		addCustomer(new Customer("Sree", "Maddika"));

	}

	public List<Customer> getCustomer() {
		return new ArrayList<Customer>(customer.values());
	}

	private boolean isCustomerAlreadyExists(Customer customerObj) {

		boolean isExists = false;

		for (Customer p : customer.values()) {
			if (p.getFirstName().equalsIgnoreCase(customerObj.getFirstName())
					&& p.getSurName().equals(customerObj.getSurName())) {
				isExists = true;
			}
		}

		return isExists;
	}

	public Customer getCustomer(int id) throws IllegalArgumentException {
		Customer p = customer.get(id);
		if (p == null) {
			throw new IllegalArgumentException("Could not find customer with id:" + id);
		}
		return p;
	}

	public int addCustomer(Customer customerObj) throws IllegalArgumentException {

		if (isCustomerAlreadyExists(customerObj)) {
			throw new IllegalArgumentException("Customer " + customerObj + " already exists.");
		}

		int id = idGen.incrementAndGet();
		customerObj.setId(id);
		customer.put(id, customerObj);

		return id;
	}

	public void updateCustomer(int id, Customer customerObj) throws IllegalArgumentException {

		if (!customer.containsKey(id)) {
			throw new IllegalArgumentException("Unable to find customer with id " + id);
		}

		customer.put(id, customerObj);

	}

	public void deleteCustomer(int id) throws IllegalArgumentException {
		if (!customer.containsKey(id)) {
			throw new IllegalArgumentException("Unable to find customer with id " + id);
		}

		customer.remove(id);

	}
}

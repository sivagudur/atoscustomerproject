package com.atos.demo.rest.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atos.demo.rest.api.model.ApiMessage;
import com.atos.demo.rest.api.model.CustomerWrapper;
import com.atos.demo.rest.api.model.Customer;
import com.atos.demo.rest.api.service.ICustomerService;
import com.atos.demo.rest.api.utils.SpringMVCUtils;

@Controller
/**
 * This class has the logic for acting on web requests for Customer.
 * 
 * @author Siva Gudur
 *
 */
public class CustomerController {

	/**
	 * A service that provides operations around managing Customer. Spring will
	 * automatically wire in an implementation of the ICustomerService interface.
	 * Since the only implementation is the CustomerService class, this will be
	 * used. The autowiring is done when the controller is loaded, which happens
	 * when the line <br />
	 * {@code <context:component-scan base-package="com.atos.demo.rest.api" />}
	 * <br />
	 * is run in the rest-servlet.xml.
	 */
	@Autowired
	private ICustomerService customerService;

	@RequestMapping(value = "/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("<h1>Hello!</h1>", HttpStatus.OK);
	}

	
	
	/**
	 * 
	 * @return a ModelAndView containing all customer objects. 
	 * @throws ApiException
	 *             if the Customer is not valid.
	 */

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ModelAndView getCustomer() {
		List<Customer> customer = customerService.getCustomer();
		return SpringMVCUtils.getOutputModel(CustomerWrapper.createNew(customer));
	}

	/**
	 * 
	 * @param person
	 *            the person object built from the request body.
	 * @return a ModelAndView containing a single customer object. Or an
	 *         ApiMessage object if the Customer  was not valid, eg if name was
	 *         missing.
	 * @throws ApiException
	 *             if the Customer is not valid.
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ModelAndView addCustomer(@Valid @RequestBody Customer customer) {

			int id = customerService.addCustomer(customer);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Customeradded with id=" + id));
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ModelAndView getCustomerById(@PathVariable(value = "id") int id) {
		
			Customer p = customerService.getCustomer(id);
			return SpringMVCUtils.getOutputModel(p);
	}

	/**
	 * Updates a stored Customer.
	 * 
	 * @param id
	 * @param customer
	 *            the new customer object.
	 * @return A customer object in a ModelAndView if successful, or an ApiMessage
	 *         in the ModelAndView if an error occured.
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ModelAndView updateCustomerById(@PathVariable(value = "id") int id,
			@Valid @RequestBody Customer customer) {

		customer.setId(id);
		customerService.updateCustomer(id, customer);

		return SpringMVCUtils.getOutputModel(customer);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCustomerById(@PathVariable(value = "id") int id) {

		customerService.deleteCustomer(id);

			return SpringMVCUtils.getOutputModel(new ApiMessage("Customer with id "+id+" successfully deleted."));
	}

	

	/**
	 * Handles exceptions of the class ApiException that are thrown by controller methods. Sets the response status code to 400 BAD_REQUEST.
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleApiException(IllegalArgumentException e,
			HttpServletResponse response) {

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		return SpringMVCUtils.getOutputModel(new ApiMessage(e.getMessage()));
	}

	/**
	 * Shows a more descriptive error message to the user when a submitted
	 * object fails validation.
	 * 
	 * @param e
	 *            the MethodArgumentNotValidException that we are handling.
	 * @return A ModelAndView containing a single ApiMessage object. We also
	 *         alter the response to have the status code 400.
	 */
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ModelAndView handleBindingException(
			MethodArgumentNotValidException e, HttpServletResponse response) {

		// Build a list of all the validation errors to show to the user.
		// WARNING this may not be a good idea on a production website because
		// it may expose internal details such as the fact you are using Java,
		// JSR-303, etc. A generic BAD_REQUEST error would probably be better.
		String errors = buildErrorString(e.getBindingResult().getAllErrors());

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		return SpringMVCUtils.getOutputModel(new ApiMessage(errors));
	}

	/**
	 * Concatenates the validation errors from the allErrors param into a single
	 * string for display to a user.
	 * 
	 * @param allErrors
	 * @return
	 */
	private String buildErrorString(List<ObjectError> allErrors) {
		StringBuilder b = new StringBuilder();

		b.append(allErrors.get(0));

		// append any remaining errors
		for (int i = 1; i < allErrors.size(); i++) {
			b.append("\n");
			b.append(String.format("%s - %s", allErrors.get(i).getObjectName(),
					allErrors.get(i).getDefaultMessage()));
		}

		return b.toString();
	}
}


To run the application:
-------------------
Import the project as a maven project into your IDE of choice. 
Look for the `com.atos.demo.rest.api.Start` class in src/test/java and run it which has embedded jetty server. You should now have the application running at http://localhost:8090/rest/


Overview
========

This is an application to demo the Customer implementation of a REST API that can return responses in both JSON and XML. 
There are three ways of requesting a specific type of response:

1. Setting the Accept header to `application/[json|xml]`
2. Adding a `?format=[json|xml]` parameter to the url.
3. Adding the format as the file type of the request. Eg instead of the url `http://localhost:8090/object`, your would have `http://localhost:8090/object.json`

URLs the servlet responds to:


+ `/customer` - This path will only respond to GET requests. The response format can be changed from the default of json to xml by using one of the 
	three methods listed above. The response is a description of a person object in json or xml.  
	The /customer* urls require basic authentication to access them. This is set up in the security context. The user name is `user` and the password is `pass`.

	Example URLs:

			
### Adding a Customer - POST request		
		http://localhost:8090/rest/customer - Use POST with a body and appropriate content type to create a new Customer.
Example 1
{"firstName":"Amish","surName":"Gudur"}
Example 2
{"firstName":"Ishaan","surName":"Maddika"}

Note: Generating sequence for id - For example 1, 2, 3 etc

### List All Customers - GET request
		
		http://localhost:8090/rest/customer
		http://localhost:8090/rest/customer.json
		http://localhost:8090/rest/customer.xml
		
### List one Customer for example with id =1  - GET request
		http://localhost:8090/rest/customer/1
		http://localhost:8090/rest/customer/1.xml
		http://localhost:8090/rest/customer/1.json
### Modifying Customer details for example id =1  - PUT request

Example 1
{"firstName":"SivaRama","surName":"Gudur"}

		
		http://localhost:8090/rest/customer/1 - with PUT request and a updated Customer object
		http://localhost:8090/rest/customer/1.xml  - with PUT request and a updated person object
		http://localhost:8090/rest/customer/1.json  - with PUT request and a updated person object
		
### Removing the Customer given their id for example id =1  - PUT request		
		http://localhost:8090/rest/customer/1 - DELETE request
		http://localhost:8090/rest/customer/1.json - DELETE request
		http://localhost:8090/rest/customer/1.xml - DELETE request

### Features

* Uses embedded Jetty
* Uses basic auth to secure some url paths
* A simple RESTful api
* Uses Spring annotations
* Uses HttpMessageConverters - allows the serialization of objects to XML and JSON. Also used for converting request bodies into Java objects.
* Security configuration isolated to a single file.
* Comments with explanations of why some features were implemented a certain way.



### Start.java

This is the class that runs an embedded Jetty server with the servlet. 



# Spring-boot-web-rest
This is a Spring Boot application for  _Web based REST_ 

__Featuriz__ guide to *Spring Framework 5*.

## Technology and Version

| __Spring__ | __Version__ |
| ---- | ---- |
| Spring Boot | 2.6.1 |
| Spring web | 5.3.13 |
| Spring Test | 5.3.13 |
| Spring JPA | 2.6.1 |
| Mariadb | 2.7.4 |
| Lombok | 1.18.22 |
| Validation | 2.6.1 |

#### How to use
This is a maven based spring boot project. Use maven to build this project and just run.  
_No other dependencies_

#### Database
Check application.properties file.
 - Entities can create tables
 - You can add mock content to db by using data.sql file - This can be load automatically, check properties file.

### Based on
This work is based on the tutorial from  
[Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Learning topics
The difference between REST and RPC:
- REST - Representational State Transfer
- RPC - Remote Procedure Call

###  Spring HATEOAS
Working with [Spring HATEOAS](https://spring.io/projects/spring-hateoas)

- This helps to write hypermedia-driven outputs.
	
	<dependency>
	  <groupId>org.springframework.boot</groupId>
	  <artifactId>spring-boot-starter-hateoas</artifactId>
	</dependency>
	

- A critical ingredient to any RESTful service is adding [links](https://tools.ietf.org/html/rfc8288) to relevant operations

	{
      "id": 1,
      "name": "Bilbo Baggins",
      "role": "burglar",
      "_links": {
      	 "self": {
      	   "href": "http://localhost:8080/employees/1"
      	 },
      	 "employees": {
      	   "href": "http://localhost:8080/employees"
      	 }
      }
    }
    

#### Simplifying Link Creation
- Create EmployeeModelAssembler by implementing _RepresentationModelAssembler interface_
- Convert Employee objects to EntityModel<Employee> objects

### Evolving REST APIs
	
	Never delete a column in a database. 
	

- Replace the field
- A "virtual" getter & setter for the old name property

#### Proper Responses
- Include a Location response header
- Return the model-based version of the saved object

	
	$ curl -v -X PUT localhost:8080/employees/3 -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'	
	* TCP_NODELAY set
	* Connected to localhost (::1) port 8080 (#0)
	> PUT /employees/3 HTTP/1.1
	> Host: localhost:8080
	> User-Agent: curl/7.54.0
	> Accept: */*
	> Content-Type:application/json
	> Content-Length: 49
	>
	< HTTP/1.1 201
	< Location: http://localhost:8080/employees/3
	< Content-Type: application/hal+json;charset=UTF-8
	< Transfer-Encoding: chunked
	< Date: Fri, 10 Aug 2018 19:52:56 GMT
	{
		"id": 3,
		"firstName": "Samwise",
		"lastName": "Gamgee",
		"role": "ring bearer",
		"name": "Samwise Gamgee",
		"_links": {
			"self": {
				"href": "http://localhost:8080/employees/3"
			},
			"employees": {
				"href": "http://localhost:8080/employees"
			}
		}
	}
	

## Building links into your REST API
- Enter __HATEOAS__ or __Hypermedia as the Engine of Application State__. 
- Instead of clients parsing the payload, give them links to signal valid actions. 
- Decouple state-based actions from the payload of data. 
- In other words, when __CANCEL__ and __COMPLETE__ are valid actions, dynamically add them to the list of links.
- Clients only need show users the corresponding buttons when the links exist.

#### Try cancelling an order:

	
	$ curl -v -X DELETE http://localhost:8080/orders/4/cancel
	> DELETE /orders/4/cancel HTTP/1.1
	> Host: localhost:8080
	> User-Agent: curl/7.54.0
	> Accept: */*
	>
	< HTTP/1.1 200
	< Content-Type: application/hal+json;charset=UTF-8
	< Transfer-Encoding: chunked
	< Date: Mon, 27 Aug 2018 15:02:10 GMT
	<
	{
	  "id": 4,
	  "description": "iPhone",
	  "status": "CANCELLED",
	  "_links": {
	    "self": {
	      "href": "http://localhost:8080/orders/4"
	    },
	    "orders": {
	      "href": "http://localhost:8080/orders"
	    }
	  }
	}
	

__HTTP 200__ status code indicating it was successful

If you try the same operation again…

	
	$ curl -v -X DELETE http://localhost:8080/orders/4/cancel
	
	* TCP_NODELAY set
	* Connected to localhost (::1) port 8080 (#0)
	> DELETE /orders/4/cancel HTTP/1.1
	> Host: localhost:8080
	> User-Agent: curl/7.54.0
	> Accept: */*
	>
	< HTTP/1.1 405
	< Content-Type: application/problem+json
	< Transfer-Encoding: chunked
	< Date: Mon, 27 Aug 2018 15:03:24 GMT
	<
	{
	  "title": "Method not allowed",
	  "detail": "You can't cancel an order that is in the CANCELLED status"
	}
	
	
__HTTP 405 Method Not Allowed__
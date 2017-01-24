# sampler

Sample Projects by jw6bars@gmail.com

##### Project: rest-sampler


##### Task:

Write a REST service that prints out something and include the unit test and a short explanation of the example code.

##### Description:

	This is a Maven project for a REST service based on Spring Boot.
	There are 3 REST Services:
		echo           : REST GET with URI parameter and plain text response
		echo-json      : REST GET with URI parameter and JSON response
		echo-json-post : REST POST accepting JSON request body and JSON response
		
	The project contains JUnit tests using the Spring Test Framework.
		RestSamplerControllerTest      : uses SpringBootTest with MockMvc.
		RestSamplerStandaloneSetupTest : uses the StandaloneMockMvcBuilder

##### To build project and run JUnit tests: 

	mvn clean package

###### To start the server:

	mvn spring-boot:run

###### To manually test the "echo" service	

	http://localhost:8080/echo
	
	http://localhost:8080/echo?text=your%20text%20here

###### To manually test the "echo-json" service	

	http://localhost:8080/echo-json
	
	http://localhost:8080/echo-json?text=your%20returned%20text%20here

###### To manually test the "echo-json-post" service	

	curl -X POST 'http://localhost:8080/echo-json-post'
	
	curl -X POST -H 'Content-Type: application/json' \
		-d '{"id":2016,"text":"your message here"}' 'http://localhost:8080/echo-json-post'
	



http://localhost:8080/sampler-terms-service-english.html

http://localhost:8080/index.html -> http://localhost:8080/api-docs


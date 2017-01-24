# Password Validation Demo Application

#### Instructions:

Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice). 
The service is meant to check a text string for compliance to any number of password validation rules. 
The rules currently known are:
 
1. Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
2. Must be between 5 and 12 characters in length.
3. Must not contain any sequence of characters immediately followed by the same sequence.
 
Include all artifacts in a zip file and please let us know how many hours you spent on the programming problem. 
The project should include a build capability in one of the following tools, Eclipse, IntelliJ, ant, or maven. 
The project should be ready for insertion into a production system. 
Show tests for the service and any constituent classes involved in fulfillment of the service. 
Also, show how to access and use the service at runtime.

#### Initial maven project was created using Spring Boot Initializer

http://start.spring.io/

+ Group Id: com.jsampler.pwvalidator
+ Artifact Id: jsampler-pwvalidator
+ Selected Dependencies: Web, Thymeleaf, DevTools

This dependency was later added to pom.xml

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>

#### Maven Build Instructions

This project is configured for Java 1.8 so you must have Java 1.8 in your PATH.

Alternatively you can use Java 1.7 if you change the java.version at the bottom of the pom.xml file.

Execute these commands to build and run the unit tests

+ mvn clean
+ mvn test

#### Maven Run Instructions

Execute this command to run the application

+ mvn spring-boot:run

To manually exercise the application, open your browser and go to 

+ http://localhost:8080/password

To change the default listening port from 8080, edit application.properties and uncomment server.port=8090 

When developing you can uncomment spring-boot-devtools in the pom.xml file to enable auto restarts


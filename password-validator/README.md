# Password Validation Demo Application

---

#### Overview:

This is a password validation demo service written in Java and using Spring Boot, IoC/DI, MVC, and Thymeleaf.
An input text string is checked for compliance with several password validation rules. 
The number of rules can be extended by adding additional implementations of the PasswordRule interface.

The current rules are:
 
1. Number of characters must be between 5 and 12 inclusive.
2. All characters must be a lowercase letter or a numerical digit, and there must be one of each.
3. There must not be a sequence of characters immediately adjacent to an identical sequence.

The project is built using Maven and can be imported as a Maven project into Eclipse or IntelliJ. 

---
 
##### JUnit Test Classes

+ PasswordControllerTest
+ LengthRuleTest
+ LowerCaseAlphaNumericOnlyRuleTest
+ RepeatedSequenceRuleTest
+ PasswordValidatorTest

---

##### Initial maven project was created using Spring Boot Initializer

http://start.spring.io/

+ Group Id: com.jsampler.pwvalidator
+ Artifact Id: jsampler-pwvalidator
+ Selected Dependencies: Web, Thymeleaf, DevTools

This dependency was later added to the pom.xml file

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>

Project developed using Spring Tool Suite

---

##### Maven Build Instructions

This project is configured for Java 1.8 so you must have Java 1.8 in your PATH.

Alternatively you can use Java 1.7 if you change the java.version at the bottom of the pom.xml file.

Execute these commands to build and run the unit tests

+ mvn clean
+ mvn test

---

##### Maven Run Instructions

Execute this command to run the application

+ mvn spring-boot:run

To manually exercise the application, open a browser and go to 

+ http://localhost:8080/password

To change the default listening port from 8080, edit application.properties and uncomment server.port = 8090


---

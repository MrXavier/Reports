# Reports
This repository is created to implement the solution for Crealytics recruitment challenge.

It's a RESTful service responsible for two simple scenarios.

In this application, there is an embedded application server and a database.

This application is running under Spring Boot.


## Used Technologies

**1. Java version 8.**

**3. JPA / Hibernate:** Mapping persistent entities in domains objects and vice versa.

**6. Logback:** Generation of logs.

**7. Spring Data JPA:** It's used to generate part of the code of the persistence layer.

**9. Jackson:** API for convert the Java data in Json and vice versa.

## Additional Technologies

**Database:** H2 database embeded in the application. The database is created during the startup of the application. In the end of the execution the database is deleted.

**Tests:** The tests are defined as use case of the Junit. The tests of rest services have: Spring Web MVC for mock of the web infrastructure; JsonPath e hamcrest are used for access and assertions in the Json content. The tests have been made available in the structure: src/test/java.

**Spring Boot:** Technology used for create a embeded enviroment of the execution, I used this technology for simplify the use of the Spring and for controle the scope of the database. In the file src/main/resources/application.yml have properties of the Spring Boot for the project.

**Maven:** Life cycle management and project build.


## Usage In Local Machine

###### Requirements
```
JDK - Java version 1.8.

Any Java IDE with support Maven.

Maven for build and dependecies.


###### After download the fonts, to install the application and test it execute the maven command:
$ mvn clean install

###### To only test the application execute the maven command:
$ mvn clean test

###### To run the application the maven command:
$ mvn spring-boot:run

###### To get the reports for mobile web and january month type the bellow line in the url
http://localhost:8080/reports?site=mobile_web&month=jan
site can be: desktop_web, mobile_web, ios and android
month can be: january, February, jan, Feb, 1, 2, and so on (see you can represent the month in 3 formats, full name, 3 letters or number representing the nth month)

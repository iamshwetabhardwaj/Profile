# Name of project 
org-with-rest

## Description 
A simple project for organization (employee, department, etc) information display via RESTful endpoints.

### Project Overview
A simple demo project for listing employee and employee department information via RESTful endpoints. 
The purpose of this project is to demonstrate usage of Spring and PostgreSQL features used for a simple implementation.

#### Configuration Instructions
Following software(s) are expected to be pre-installed on your system to run this project - 
* Java (v8)
* Maven
* Spring
* PostgreSQL

#### Technology Stack Used
- Java (JDK v8)
- Spring Boot (v2.1.7.RELEASE)
- Spring MVC (v5.1.9.RELEASE)
- Hibernate (v5.3.10.Final)
- PostgreSQL (v42.2.6)
- Swagger (v2.9.2)

#### Features being demonstrated
1. RESTful Endpoints with Spring MVC based mappings for 
  - GET
  - POST
  - DELETE
2. AOP Implementation for Logging with Pointcuts for
  - Before 
  - Around
  - AfterThrowing
3. Spring Data JPA Implementations for
  - native queries
  - regular JPA methods 
  - Transaction Management and Propagation
4. Hibernate Entity Relationships
  - Composite Primary Key
  - Attribute Overrides
  - Many to Many relationship mappings
5. PostgreSQL queries 
  - Schema creation
  - Table creation, only if table does not exist
  - Composite Primary and Foreign keys
  - Insert data in table only if prior record does not exist
6. Swagger
  - API documentation available after running the JAR.

#### Operational Instructions
1. Open a command line tool and navigate to the directory/folder where the JAR is placed.
    a. Run the following command - mvn clean install
    b. Alternatively, can also run the command - java –jar org-with-rest-0.0.1-SNAPSHOT.jar


#### A list of files included
- ├───src
- │   ├───main
- │   │   ├───java
- │   │   │   └───io
- │   │   │       └───demo
- │   │   │           └───orgwithrest
- │   │   │               │   OrgWithRestApplication.java
- │   │   │               │
- │   │   │               ├───controller
- │   │   │               │       DepartmentController.java
- │   │   │               │       EmployeeController.java
- │   │   │               │
- │   │   │               ├───dao
- │   │   │               │       DepartmentRepository.java
- │   │   │               │       EmployeeRepository.java
- │   │   │               │
- │   │   │               ├───logger
- │   │   │               │       OrgWithRestLogger.java
- │   │   │               │
- │   │   │               ├───model
- │   │   │               │       Department.java
- │   │   │               │       Employee.java
- │   │   │               │       Primary.java
- │   │   │               │
- │   │   │               └───service
- │   │   │                   │   DepartmentService.java
- │   │   │                   │   EmployeeService.java
- │   │   │                   │
- │   │   │                   └───impl
- │   │   │                           DepartmentServiceImpl.java
- │   │   │                           EmployeeServiceImpl.java
- │   │   │
- │   │   └───resources
- │   │       │   application.properties
- │   │       │   data.sql
- │   │       │   schema.sql
- │   │       │
- │   │       ├───static
- │   │       └───templates

#### Additional Notes
A postman test suite is also included which lists all the APIs exposed in this project and also shows sample requests which demonstrate error handling and successful request formats.
A snapshot of the Swagger UI is also available which lists the endpoint documentation.

##### Developer Contact Information
- budbakforgit@gmail.com

##### Changelog

 

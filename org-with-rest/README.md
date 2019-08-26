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


#### Installation Instructions
1. Open a command line tool and navigate to the directory/folder where the JAR is placed.
2. Run the following command - mvn clean install


#### Operating Instructions
1. Open a command line tool and navigate to the directory/folder where the JAR is placed.
2. Run the following command - mvn spring-boot:run

#### A list of files included
├───src
│   ├───main
│   │   ├───java
│   │   │   └───io
│   │   │       └───demo
│   │   │           └───orgwithrest
│   │   │               │   OrgWithRestApplication.java
│   │   │               │
│   │   │               ├───controller
│   │   │               │       DepartmentController.java
│   │   │               │       EmployeeController.java
│   │   │               │
│   │   │               ├───dao
│   │   │               │       DepartmentRepository.java
│   │   │               │       EmployeeRepository.java
│   │   │               │
│   │   │               ├───logger
│   │   │               │       OrgWithRestLogger.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       Department.java
│   │   │               │       Employee.java
│   │   │               │       Primary.java
│   │   │               │
│   │   │               └───service
│   │   │                   │   DepartmentService.java
│   │   │                   │   EmployeeService.java
│   │   │                   │
│   │   │                   └───impl
│   │   │                           DepartmentServiceImpl.java
│   │   │                           EmployeeServiceImpl.java
│   │   │
│   │   └───resources
│   │       │   application.properties
│   │       │   data.sql
│   │       │   schema.sql
│   │       │
│   │       ├───static
│   │       └───templates

#### Additional Notes
A postman test suite is also included which lists all the APIs exposed in this project and also shows sample requests which demonstrate error handling and successful request formats.

##### Developer Contact Information
- budbakforgit@gmail.com

##### Changelog

 
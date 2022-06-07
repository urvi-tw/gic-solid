# SOLID

[![CircleCI](https://circleci.com/gh/India-Dev-Bootcamp/gic-solid/tree/master.svg?style=svg&circle-token=e9b0e382e1d588289d06bc64a18ea56c09f211d9)](https://circleci.com/gh/India-Dev-Bootcamp/gic-solid/tree/master)

## History

* This is a legacy code base
* Most of the developers who were involved in this project have left
* This code was part of a monolith written in core java
* Later it was carved out as a micro-service 

## Introduction
* This service provides REST APIs used by service engineers of an online retailer
* It has APIs related to inventory management, order management & customer complaint management
* Start the application and access all the APIs at http://localhost:8080/swagger-ui.html
* stagging environment can be accessed at: https://gicsolidurvi.herokuapp.com/swagger-ui.html
* Production is running at https://gic-inventory-app.herokuapp.com/swagger-ui.html

## Future plan
* There are a set of stories, bugs, features etc. planned around this project.
* This project should evolve as a proper micro-service and code quality should improve.
* Thoughtworks consultants are hired to work on this.

## Workshop Notes
* Data need not be persisted in DB
* Ignore foreign key constraints and need not emphasis much on cross data validation.
* Authorization and Authentication need not be considered
* A set of customer complaints are being seeded during the boot-up and it can also be added through REST API
* The service contacts external services for usecases like
  * Notifying service engineers for processing complaints and they complete it within their SLA.
  * Notifying customers through email, phone etc.
  * Notifying dispatch team about shipping products

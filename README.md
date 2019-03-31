# URL Shortener

This application runs a URL shortener which takes a long URL and returns a short URL.
It runs on Spring Boot and interfaces with Postgres SQL.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 1.8 or greater
Maven
Postgres SQL
```

### Installing

This project builds a jar with dependencies using the spring-boot-maven-plugin Maven plugin
defined in the pom.xml.

Download the project.

```
git clone https://github.com/valtera45/urlshortener.git
```

Create a DEV profile:
* Copy the application-dev-template.properties in src/main/resources to application-dev.properties.
* Configure the application-dev.properties to match your local Postgres installation.

Run Maven Clean Install

```
cd urlshortener
mvn clean install
```

Start the application using the jar built in the target directory

```
java -jar target/urlshortener-0.0.1-SNAPSHOT.jar
```

Navigate to the application at http://localhost:8080

## Running the tests

The tests are run during the install phase of the Maven build.
Tests can also be run individually if the project is imported into an IDE.
Tests can be skipped running -DskipTests=true under the mvn command.
There is a controller web integration test and a service layer test.

```
mvn clean install
```

```
mvn clean install -DskipTests=true
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Spring container
* [Maven](https://maven.apache.org/) - Dependency Management
* [Postgres](https://www.postgresql.org/) - Database server


## Authors

* **Christopher Wright** - *Initial work* - [valtera45](https://github.com/valtera45)

# Coupon Dev Project - Backend

This web application uses a Java backend with Dropwizard as the framework to build the RESTful web service.
The offer data is read from resources folder in src/main. The data is made up of 3 csv files that are used to build a SQLite database.

## Source Code Overview

### pom.xml
This file is used to get all the dependencies needed for the app.

## src/main/configuration Directory
The local run configurations are defined here.

### src/main/java Directory
This directory holds the entry point of the service.

OffersServiceApplication will first setup swagger to use. It will then establish CORS headers so browsers can call the API successfully.
The SQLite database is then configured correctly with the tables and data. The resource is then created.

OffersServiceConfiguration is where the swagger setup is created and read from the configuration file.

### src/main/java/api Directory
The Java classes for the JSON responses are defined here. OfferInfo is for the /offers endpoint. OfferDetails is for the /offers/details endpoint.

### src/main/java/dao
The data access object to retrieve the data from the database is here. Two functions are specified for the different database calls used.
The database loader will use Jdbi to access the data in the SQLite database using SQL statements.
The Mapper files are used to help map the data to the Java classes in the api directory.

### src/main/java/health Directory
A simple service health check is defined here that will return true if the service is healthy. The healtcheck can be seen here
[http://localhost:8081/healthcheck](http://localhost:8081/healthcheck).

### src/main/java/resources Directory
The REST API calls are defined here. The endpoints will call the dao functions to return the data as JSON objects.

### Trade-offs
Java was used as the backend due to its simplicity and object-oriented characteristics. It is also high performing and allows for multi-threading.

Dropwizard is very light and fast. A RESTful web service is easy to setup using dropwizard's many libraries. It also has health monitoring.

Maven makes it easy to run the app as well as run tests. It also downloads dependencies automatically.

SQLite is open source and does not require installation or setup. It is serverless.

JUnit is helpful for writing unit tests in Java.

### Additional features
Caching is a great additional feature. Using the library Guava Cache makes caching simple. After the database calls are made, the results can be cached and returned in subsequent calls for faster data retrieval.

# Getting Started

## Available Scripts

### Command Line

In this directory, you can run the below commands.

Assuming home brew is installed, you can install maven to build the project.
```sh
brew install maven
```

Build the project.
```sh
mvn package
```

Runs the app in the development mode.<br>
```sh
java -jar target/coupon-dev-project-1.0-SNAPSHOT.jar server src/main/configuration/local-run-config.yml
```

Open [http://localhost:8080/offers](http://localhost:8080/offers) to view it in the browser.
Open [http://localhost:8080/swagger#/](http://localhost:8080/swagger#/) to view swagger and interact with the REST API in the browser.

Open [http://localhost:8081/metrics](http://localhost:8081/metrics) to see metrics.
Open [http://localhost:8081/threads](http://localhost:8081/threads) to see thread information.

If you make any changes, you need to stop the java process and restart it to see changes.
If you get errors about the port already in use, use this command
```sh
lsof -i :8080
```
to find the process still running and run the command
```sh
kill -9 <pid>
```
to stop the process and free up the port.

### IntelliJ

If IntellJ is installed, the coupon-dev-backend folder can be opened and the file `OffersServiceApplication` can be run using the program arguments
```sh
server src/main/configuration/local-run-config.yml
```

## Run the following commands to run tests

### Command Line
This command will run all the tests.
```sh
mvn clean test
```

### IntelliJ
Right click on the directory src/test/java and click on Run 'All Tests'.
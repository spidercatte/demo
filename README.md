# ETL Coding Assignment Application

## Development

### Setup Local Environment

#### Prerequisites
- Ensure that you have Java Development Kit (JDK) installed on your machine. The application is developed and tested with JDK 1.7.
- Install [Maven](https://maven.apache.org/install.html) on your machine. The application is built and managed using Maven.

#### Install Required Libraries and Dependencies
Before building and running the application, make sure to install the necessary libraries and dependencies by running the following Maven command:
```
mvn -U install
```

This command will download the necessary libraries and dependencies, compile the code, and build the project.
```
mvn clean 
mvn compile
```

### Run Locally
After setting up the environment, follow these steps to run the Spring Boot REST application locally:

### Install MsqlServer
- Install [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) on your machine.

### Initiate the database
Login to your mysql server, normally with root credential, unless you created new schemas.

```
 mysql -u root
```

Run the following scripts to instantiate create of databases MySQL servers

On db1
```
CREATE DATABASE db1;
```
On db2
```
CREATE DATABASE db2;
```

List the databases, to verify that it has been created
```
Show databases;
```

Run the liquibase change logs to create the tables and populate the test data.
The connection to their respective databases are defined in their property files.
```
mvn liquibase:update -Dliquibase.propertyFile=path_to/src/main/resources/liquibasedb1.properties
mvn liquibase:update -Dliquibase.propertyFile=path_to/src/main/resources/liquibasedb2.properties
```

### Run Tests
```
mvn test
```

#### Run the Application
This command will start the Spring Boot application locally.
```
mvn spring-boot:run
```

Alternatively you can use run the application by right-clicking on the DemoApplication.java if you are using the intellij IDE

#### Access the Application
Open a web browser and navigate to http://localhost:8080/api/wagers to access the running application.

#### Test the Endpoints
Use Postman collection (`etl.postman_collection.json`) or curl to test the REST endpoints exposed by the application.

```
curl 'localhost:8080/api/wagers/summary'
```

You can alternatively use the swagger url to test 

http://localhost:8080/swagger-ui/index.html#/etl-controller/getWagerSummary

#### Shutdown the Application
To stop the running application, press Ctrl + C in the terminal where the application is running.

## Packaging

```
mvn clean package
```

### Create a Docker Image using BuildPak
```
mvn spring-boot:build-image

```

### Create a Docker Image manually
```
docker build -t your-image-name --build-arg JAR_FILE=demo-0.0.1-SNAPSHOT.jar .

```



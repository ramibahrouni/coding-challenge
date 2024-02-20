## High-level design
At a high-level, we need some following services (or components) to handle the functional requirements:

![challenge-architecture](https://raw.githubusercontent.com/ramibahrouni/coding-challenge/main/external-files/challenge-architecture.png)


**Ingredients Service**: Get list of Available ingredients, manage to update ingredients quantity.

**User Service**: manage all users service and create new users.

**Order Service**: manages raw orders before send quantity review and cost calculation.

**service-discovery**: Eureka instance that manages to register other services.

**config-server**: manages raw orders before send quantity review and cost calculation.

**API Gateway**: Route requests to multiple services using a single endpoint.
This service allows us to expose multiple services on a single endpoint and route to the appropriate service based on the request.

### Defining data model
   In this part, we describe considerations for managing data in our architecture. For each service, we discuss data schema and datastore considerations.

   In general, we follow the basic principle of microservices is that each service manages its own data. Two services should not share a data store.

### Development approaches (Why?)
### 1. Security:

   To ease and speed up development, we relied on api-gateway for authentication, which means whenever the user gets authentication, that the access is granted. As mentioned, the approach is only for devolpment or test purposes. In real world, we need more in order to make the whole system more secure.
    --> As an alternative, we would implement 2 steps to improve the whole security architecture as the following:
- Implement/Add authentication and authorization component such as an Identity Provider such **Keycloack**, **Auth0**, ....
- Apply zero trust security approach where each service validates the coming request before serving.

### 2. Externalized Configuration
We used Spring Config Server as our way to externalize the configuration files for the services.
We used **Discovery-First** approach since we do have discovery-service and in order to avoid the hassle of creating and maintaining URLs.

### 3. Caching
In the order-service, we have a very good case of using caching since we are dealing with making calls to ingredients-service multiple times and in the order-servie we need to keep the order warm in order to avoid call it back from database.


## Software development principles

### Separation of Concerns
- Separating a system into multiple distinct microservices, such that each service addresses a separate concern (ingredients, user, order...).
- In each service, break program functionality into separate layers.
- AOP to separate of cross-cutting concerns.

### Single Responsibility Principle
Every class should have a single responsibility, and that responsibility should be entirely encapsulated by the class. Responsibility can be defined as a reason to change, so a class or module should have one, and only one, reason to change.

**What applied:** break system into multiple services, each services has only one responsibility. In each services, break into multiple layers, each layers were broken into multiple classes, each class has only one reason to change.

## Application default configuration
To make it easier for development process, we still expose these ports on the local machine to send request directly with services or to view actual data in the data stores. 
In production environment, we leverage the infrastructure to make the downstream services become unreachable from the client, we only expose one single point - API Gateway.

| Service               | Port |
|-----------------------| --   |
| api-gateway           | 8087 |
| ingredients-service   | 8085 |
| user-service          | 8082 |
| order-service         | 8084 |
| discovery-service     | 8761 |
| config-server         | 8888 |

_Note_: for development purpose, we could bypass authentication by adding "Username: ```<your-test-username>```" to HTTP Header when we send request to downstream services.

## How to run the application
### Setup development workspace
The setup development workspace process is simpler than ever with following steps:
1. Install [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
1. Install [Maven](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/).
1. Install [Docker](https://www.docker.com/products/docker-desktop/).
1. Clone this project to your local machine.
2. ```cd infra && docker-compose up ```
3. Open the pom.xml file and open as a project using Intellij IDEA.


### Run a microservice
You can run Spring Boot microservice in different ways, but first make sure you are in the root directory of the microservice you want to run:
- Run with Spring Boot: ```mvn spring-boot:run```

For each microservice, we will follow common 4 layers architecture:
- **Controller**: Handle HTTP request from client, invoke appropriate methods in service layer, return the result to client.
- **Service**: All business logic here. Data related calculations and all.
- **Repository**: all the Database related operations are done here.
- **Entity**: persistent domain object -  table in Databases.

### Frameworks and Libraries
The Frameworks/Libraries used in the project and their purposes:
- spring-cloud-starter-netflix-eureka-server : Eureka Server (Registry Service). This library allows services to find and communicate with each other without hard-coding hostname and port.
- spring-cloud-starter-netflix-eureka-client : Eureka Client, for registering the service with Service Registry.
- - spring-cloud-starter-config : Config client for getting centralized properties stored in Github.
- spring-boot-starter-web : for building REST API.
- spring-boot-starter-test : Starter for testing Spring Boot applications with libraries including Test-containers, JUnit, Hamcrest and Mockito.
- spring-boot-starter-data-jpa: for using Spring Data JPA with Hibernate.
- spring-boot-starter-security: for using Spring Security.
- spring-security-test: for the testing Spring Security.
- test-containers: for providing tech components for more realistic testing.
- rest-assured: for providing simplicity while testing and validating REST services.

### Heavy Components
- PostgreSQL
- Microsoft SQL Server
- Apache Kafka
- Debezium (cdc component)
- Kafka Connect

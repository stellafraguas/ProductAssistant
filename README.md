# ProductAssistant

ProductAssistant is a backend service for managing product and category data using Spring Boot.

## Technologies

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database (in-memory)
- PostgreSQL
- Maven

## Prerequisites

- Java 21
- Maven 3.8+
- PostgreSQL installed and running

## How to Run

Clone the repository and run the application using Maven:

```bash
git clone https://github.com/stellafraguas/ProductAssistant.git
cd ProductAssistant
./mvnw spring-boot:run
```

### H2 Console

Once the application is running, you can access the H2 database console at:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(leave blank)*

## Running Tests

To run the test suite:

```bash
./mvnw test
```

## Frontend

The frontend for this application is maintained in a separate repository.

[Link to frontend repository](https://github.com/stellafraguas/product-assistant-ui.git) 

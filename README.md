# EMICalculator-Springboot

## Description
This project is a Spring Boot application that provides an API for calculating EMI (Equated Monthly Installment) for loans. It includes a service for EMI calculation, API endpoints for handling loan requests, and an in-memory database to store EMI outcomes.

## Tech Stack
- Java 17
- Spring Boot 3.2
- Maven 3.X
- H2 Database (for in-memory storage)

## Getting Started
To run the application locally, follow these steps:

1. Ensure that Java 17 and Maven 3.X are installed on your system.
2. Clone the repository.
3. Navigate to the project root directory.
4. Run the application using the following Maven command:
```bash
mvn spring-boot:run
```

5. Once the application is running, you can use tools like Postman to send requests to the API endpoints:

### Testing and Endpoints
Both Unit and Integration tests, covering a wide range of test cases. To execute these tests, use the following command:

- Using Java command
```bash
 mvn clean test
``` 

We have an endpoint with this application

- Calculate EMI: POST request to http://localhost:8080/api/loan
  - Sample payload:
  ```bash
    {
      "loanType": "Car",
      "loanValue": 12990,
      "interestRate": 10.75,
      "loanTerm": 4,
      "email": "test@abc.com"
    }
    ```
  - Sample Response
  ```bash
    {
      "id": 1,
      "loanType": "Car",
      "loanValue": 12990.0,
      "interestRate": 10.75,
      "loanTerm": 4,
      "email": "test@abc.com",
      "emi": 334,
      "timestamp": "2023-12-04T18:26:33.0920963"
    }
    ```

  - Also handled multiple validations according to the requirements
  - We will get error response with the corresponding fields if any wrong input data
  - Payload 
  ```bash
    {
      "loanType": "Car",
      "loanValue": 12990,
      "interestRate": 105,
      "loanTerm": 40,
      "email": "test@abc.com"
    }
    ```
  - Response
  ```bash
    [
      {
        "fieldName": "interestRate",
        "errorMessage": "Interest rate must be between 1 and 100"
      },
      {
        "fieldName": "loanTerm",
        "errorMessage": "Loan term must be between 1 and 30"
      }
    ]
  ```
## Database
This project uses an in-memory H2 database to store the outcomes of the EMI calculations.

- Navigate to http://localhost:8080/h2-console to access the console. You can refer the database properties in application properties.

## Note:
- As this API developed with Angular app, we can run both angular and spring boot app together to use UI to interact with.


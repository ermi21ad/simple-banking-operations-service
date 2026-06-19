# Simple Banking Operations Service

A Spring Boot REST API for basic banking operations such as application submission, account validation, transaction processing, and transaction reversal.

## Tech Stack

* Java 18
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok

## Features

* Bank and Branch Management
* Application Submission
* Account Validation
* Transaction Processing
* Transaction Reversal
* Data Seeding
* Global Exception Handling

## Project Structure

```text
src/main/java
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── model
```

## Sample Seed Data

### Banks

* Commercial Bank of Ethiopia
* Awash Bank
* Dashen Bank

### Branches

* Addis Ababa Main
* Bole Branch
* Piassa Branch
* Megenagna Branch
* Sarbet Branch
* Mexico Branch

## API Endpoints

### Submit Application

```http
POST /api/applications/submit
```

```json
{
  "bankId": 1,
  "branchId": 1,
  "accountName": "Ermias Abebe",
  "accountNumber": "10000001"
}
```

### Validate Account

```http
POST /api/validate-account
```

### Process Transaction

```http
POST /api/transactions/process
```

### Reverse Transaction

```http
POST /api/reverse
```

```json
{
  "transactionId": "TXN-12AF16487B02",
  "reason": "Duplicate transaction"
}
```

## Run Locally

### Create Database

```sql
CREATE DATABASE bank_service;
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_service
spring.datasource.username=postgres
spring.datasource.password=password
```

### Run Application

```bash
mvn clean install
mvn spring-boot:run
```

Application URL:

```text
http://localhost:8081
```

## Author

Ermias Abebe 
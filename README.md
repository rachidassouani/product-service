# Product Management REST API

This is a Spring Boot application that provides a REST API for managing products. It offers CRUD (Create, Read, Update, Delete) operations for products with additional features for performance and data integrity.

## Features

- CRUD operations for products
- Paginated product listing for improved performance
- Database migration using Flyway
- API documentation with Swagger (OpenAPI)
- Unit and integration tests
- Input validation for product fields
- DTO pattern implementation
- Request logging for all endpoints

## Tech Stack

- Java
- Spring Boot
- Gradle
- MySQL
- Flyway
- Swagger (OpenAPI)

## Getting Started

### Prerequisites

- Java JDK 21
- Gradle
- MySQL

### Installation

1. Clone the repository:
   `git clone git@github.com:rachidassouani/product-service.git`


2. Navigate to the project directory:
   cd product-service

3. Build the project:
   gradle build

4. Run the application:
   gradle bootRun

The application should now be running on `http://localhost:8080`.

## API Documentation

The API documentation is available via Swagger UI. After starting the application, you can access it at:
`http://localhost:8080/swagger-ui.html`


## Database Migration

This project uses Flyway for database migration. Migrations are automatically applied when the application starts.

## Testing

To run the tests, use the following command:
gradle test


This will run both unit and integration tests.

## Input Validation

Input validation is implemented for the following fields when saving or updating products:
- Title: max 100 chars
- Subtitle: max 200 chars
- Description: max 1000 chars

## Logging

All incoming requests to the API endpoints are logged for monitoring and debugging purposes.

## Upcoming Features

- Product image storage
- CRUD operations for product ratings

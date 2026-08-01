# Smart Expense Tracker API

## Overview

Smart Expense Tracker API is a RESTful backend application built using Java and Spring Boot. It allows users to manage daily expenses through REST APIs without using a database. All data is stored in memory using a ConcurrentHashMap.

This project was developed as part of the Diligent AI Backend Assignment.

---

## Features

- Add a new expense
- View all expenses
- Filter expenses by category
- Calculate total expenses
- Calculate total expenses by category
- Delete an expense
- Input validation
- Global exception handling
- Swagger/OpenAPI documentation
- Unit testing using JUnit and Mockito

---

## Technology Stack

- Java 21
- Spring Boot
- Maven
- Spring Web
- Spring Validation
- Lombok
- Swagger (OpenAPI)
- JUnit 5
- Mockito

---

## Project Structure

```text
src
├── main
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── dto
│   ├── exception
│   └── config
│
└── test
    ├── service
    └── controller
```

---

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /expenses | Add a new expense |
| GET | /expenses | Get all expenses |
| GET | /expenses/category/{category} | Get expenses by category |
| GET | /expenses/total | Get total expenses |
| GET | /expenses/total/{category} | Get total expenses by category |
| DELETE | /expenses/{id} | Delete an expense |

---

## Swagger

After running the application:

http://localhost:8080/swagger-ui/index.html

---

## Running the Project

Clone the repository:

```bash
git clone https://github.com/sanjay21-06/expense-tracker.git
```

Go to the project:

```bash
cd expense-tracker
```

Run the application:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## Running Tests

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

---

## Sample Request

POST /expenses

```json
{
    "title": "Pizza",
    "amount": 450,
    "category": "Food",
    "date": "2026-08-02"
}
```

---

## Sample Response

```json
{
    "id": 1,
    "title": "Pizza",
    "amount": 450,
    "category": "Food",
    "date": "2026-08-02"
}
```

---

## Author

**Bontha Sanjay Nath Reddy**

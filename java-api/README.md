# Java API - Account Management System

## Overview
This is a Spring Boot REST API for account management, converted from .NET to Java as part of Issue #2: "將.NET轉換為JAVA"

## Project Structure
```
java-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/api/
│   │   │   ├── ApiApplication.java          # Main application entry point
│   │   │   ├── controller/
│   │   │   │   └── AccountController.java   # REST API endpoints
│   │   │   ├── model/
│   │   │   │   └── Account.java             # Account entity
│   │   │   ├── repository/
│   │   │   │   └── AccountRepository.java   # Data access layer
│   │   │   └── service/
│   │   │       └── AccountService.java      # Business logic
│   │   └── resources/
│   │       └── application.properties       # Configuration
│   └── test/
└── pom.xml                                   # Maven dependencies

```

## Technologies Used
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (development)
- **PostgreSQL** (production ready)
- **Maven**

## API Endpoints

### Account Management
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{id}` - Get account by ID
- `GET /api/accounts/{id}/balance` - Get account balance
- `POST /api/accounts` - Create new account
- `PUT /api/accounts/{id}` - Update account
- `DELETE /api/accounts/{id}` - Delete account

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

1. **Build the project:**
```bash
cd java-api
mvn clean install
```

2. **Run the application:**
```bash
mvn spring-boot:run
```

3. **Access the API:**
- API Base URL: http://localhost:8080/api/accounts
- H2 Console: http://localhost:8080/h2-console

### Testing the API

**Create an account:**
```bash
curl -X POST http://localhost:8080/api/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": "ACC001",
    "accountHolderName": "John Doe",
    "accountType": "SAVINGS",
    "balance": 1000.00
  }'
```

**Get all accounts:**
```bash
curl http://localhost:8080/api/accounts
```

**Get account by ID:**
```bash
curl http://localhost:8080/api/accounts/1
```

## Configuration

### Database Configuration
The application uses H2 in-memory database by default. To use PostgreSQL:

1. Uncomment PostgreSQL configuration in `application.properties`
2. Update database credentials
3. Ensure PostgreSQL is running

## Features Converted from .NET

✅ RESTful API endpoints  
✅ Entity/Model layer with JPA annotations  
✅ Repository pattern with Spring Data JPA  
✅ Service layer with business logic  
✅ Dependency injection with Spring  
✅ Database integration  
✅ CRUD operations  
✅ Transaction management  

## Related Issue
This project addresses GitHub Issue #2: "將.NET轉換為JAVA"
- Repository: kimikokao/AI-SDLC-lab
- Issue URL: https://github.com/kimikokao/AI-SDLC-lab/issues/2

## Next Steps
- [ ] Add unit tests
- [ ] Add integration tests
- [ ] Implement authentication/authorization
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Deploy to production environment

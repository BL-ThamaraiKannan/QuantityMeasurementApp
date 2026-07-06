# Quantity Measurement App

A progressive Java application that demonstrates unit-of-measurement conversions,
arithmetic operations, and a fully layered REST API — built incrementally across
17 use cases (UC1–UC17) from plain Java through to Spring Boot with JPA persistence.

---

## Overview

The **Quantity Measurement App** is a Java project developed incrementally to
showcase clean-code principles, design patterns, and enterprise-level architecture:

- **Measurement types**: Length, Weight, Volume, and Temperature
- **Operations**: Equality checks, unit conversion, addition, subtraction, division
- **Design patterns**: Generic types, Strategy (via `IMeasurable`), N-Tier architecture
- **Backend**: Spring Boot REST API with H2 in-memory persistence and Spring Security

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.7 |
| Build Tool | Maven |
| Database | H2 (in-memory, auto-configured) |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security |
| Testing | JUnit 5 |

---

## Architecture

The final application (UC15–UC17) follows a clean **N-Tier architecture**:

```
Client (HTTP)
    │
    ▼
┌──────────────────────────────┐
│     Controller Layer         │  REST endpoints, request validation
│  QuantityMeasurementController│
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Service Layer           │  Business logic, unit conversion, arithmetic
│  IQuantityMeasurementService │
│  QuantityMeasurementServiceImpl│
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│     Repository Layer         │  Data access (cache + JDBC → JPA)
│  IQuantityMeasurementRepository│
│  QuantityMeasurementCacheRepository│
│  QuantityMeasurementDatabaseRepository│
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Entity / DTO Layer      │  JPA entities, request/response DTOs
│  QuantityMeasurementEntity   │
│  QuantityDTO / QuantityInputDTO│
└──────────────────────────────┘
```

---

## Project Structure

```
QuantityMeasurementApp/
├── QuantityMeasurement/                    # Phase 1–3: Plain Java implementation
│   └── com/src/
│       ├── main/
│       │   ├── IMeasurable.java            # Core measurement interface
│       │   ├── Quantity.java               # Generic Quantity<U> class
│       │   ├── LengthUnit.java             # Length unit enum (FEET, INCH, YARD, CM)
│       │   ├── WeightUnit.java             # Weight unit enum (KG, GRAM, TONNE)
│       │   ├── VolumeUnit.java             # Volume unit enum (LITRE, GALLON, ML)
│       │   ├── TemperatureUnit.java        # Temperature unit enum (CELSIUS, FAHRENHEIT)
│       │   ├── QuantityLength.java         # Length-specific quantity operations
│       │   ├── QuantityWeight.java         # Weight-specific quantity operations
│       │   ├── QuantityMeasurementApp.java # Application entry point
│       │   ├── controller/                 # UC15: REST controller
│       │   ├── dto/                        # Data Transfer Objects
│       │   ├── entity/                     # JPA entity (UC16)
│       │   ├── exception/                  # Custom exceptions
│       │   ├── model/                      # Domain models
│       │   ├── repository/                 # JDBC repository (UC16)
│       │   ├── service/                    # Service interface + implementation
│       │   └── util/                       # DB connection + initializer
│       └── test/
│           ├── QuantityMeasurementAppTest.java  # Equality & conversion tests
│           ├── QuantityArithmeticTest.java      # Arithmetic operation tests
│           └── QuantityRefactorTest.java        # Refactor validation tests
│
└── quantity-measurement-app/               # Phase 4–5: Spring Boot application
    ├── pom.xml
    └── src/
        ├── main/java/com/app/quantity_measurement_app/
        │   ├── QuantityMeasurementAppApplication.java
        │   ├── config/SecurityConfig.java
        │   ├── controller/QuantityMeasurementController.java
        │   ├── dto/                        # QuantityDTO, QuantityInputDTO, QuantityMeasurementDTO
        │   ├── exception/GlobalExceptionHandler.java
        │   ├── model/OperationType.java
        │   ├── model/QuantityMeasurementEntity.java
        │   ├── repository/QuantityMeasurementRepository.java
        │   └── service/
        │       ├── IQuantityMeasurementService.java
        │       └── impl/QuantityMeasurementServiceImpl.java
        ├── main/resources/application.properties
        └── test/
            ├── QuantityMeasurementAppApplicationTests.java
            └── controller/QuantityMeasurementControllerTest.java
```

## REST API Endpoints (UC17)

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/quantity/compare` | Compare two quantity measurements |
| `POST` | `/quantity/convert` | Convert a quantity to a target unit |
| `POST` | `/quantity/add` | Add two quantities |
| `POST` | `/quantity/subtract` | Subtract one quantity from another |
| `GET` | `/quantity/history` | Retrieve persisted measurement history |

### Example Request — Convert

```json
POST /quantity/convert
Content-Type: application/json

{
  "value": 1.0,
  "unit": "FEET",
  "targetUnit": "INCH"
}
```

### Example Response

```json
{
  "result": 12.0,
  "unit": "INCH"
}
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Clone the repository

```bash
git clone https://github.com/BL-Stalin/QuantityMeasurementApp.git
cd QuantityMeasurementApp
```

### Run the Spring Boot application (UC17)

```bash
cd quantity-measurement-app
mvn spring-boot:run
```

The application starts on `http://localhost:8080` with an H2 in-memory database
auto-initialized on startup.

### Access the H2 Console (development)

```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:quantitymeasurementdb
Username: xyz
Password: (leave blank)
```

---

## Running Tests

### Plain Java tests (UC1–UC14)

```bash
cd QuantityMeasurement
javac -cp .:junit-5.jar com/src/test/*.java
java  -cp .:junit-5.jar org.junit.platform.console.ConsoleLauncher --scan-class-path
```

### Spring Boot tests (UC17)

```bash
cd quantity-measurement-app
mvn test
```

Test classes:
- `QuantityMeasurementAppTest` — equality and conversion scenarios
- `QuantityArithmeticTest` — addition, subtraction, division
- `QuantityRefactorTest` — validates DRY refactoring correctness
- `QuantityMeasurementControllerTest` — Spring MVC controller integration tests

---

## Branch Strategy

```
main  ←── dev  ←── feature/ucN-*
```

- `main`: stable, production-ready commits
- `dev`: integration branch — all features merged here
- `feature/ucN-*`: individual use-case branches (one per UC)

---

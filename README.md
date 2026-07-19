# BookFlow API

Backend API powering an online bookstore built with Spring Boot.

## Stack
- Java 25 toolchain
- Spring Boot 4.0.2
- Spring Security
- Spring Data JPA 
- Spring AI 
- Spring Kafka 
- Spring Cloud (future)
- PostgreSql + Liquibase
- OpenAPI (springdoc 3.0.1)
- Spock
- Kafka 4.2.1
- Docker
- Hibernate 

## Project layout
- Application code: `src/main/java/com/book-store/api`
    - Feature modules: `security`, `order`, `catalog`, `cart`, `payment`, `shipping`, `reccomendation`, `review`, `notification`
    - Shared: `shared`
- Resources/config: `src/main/resources` (Liquibase in `db/changelog`)
- Unit tests: `src/test/java`

## Prerequisites
- JDK 25
- Docker (for local MySQL via compose)

## Local development
Start MySQL:
```bash
docker-compose up -d
```

The app starts on `http://localhost:8080`.
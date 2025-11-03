# YUCircle

A social media application for York University students.

# Features
- User registration, login, email verification.
- View and edit your own profile.
- Make a post to find new friends.
- Upload your schedule to find people on campus at the same times as you.

# Tech Stack
- Backend: Java 21, Spring Boot, Spring Data JPA
- Frontend: React, Tailwind CSS
- Database: PostgreSQL
- Build Tools: Maven (Backend), npm (Frontend)
- Others: REST API, JWT Authentication

# Getting started

Follow the instructions below to get the project running.

Required tech-stack:
- Java 21.0.8 (Maven)
- Node.js v22.19.0
- PostgreSQL 13+

### Frontend

```
cd ./frontend/YUCircle

# Install dependencies
npm install

# Run frontend
npm run dev
```

### Backend

```
cd ./backend

Create a PostgreSQL database
(i.e in pgAdmin or using CLI)

# Adjust the database properties in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vampz_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

# Build and run backend
mvn clean install
mvn spring-boot:run
```

# Contributors

- Jason Deng
- Alice Luong
- Ejaaj Ahmed
- Brandon Cusato
- David Oredina
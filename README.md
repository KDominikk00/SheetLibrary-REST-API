
![SheetLibrary Spring Boot REST API]([https://imgur.com/vSMJYsK](https://imgur.com/a/Lp02Orx))

This repository contains the backend for the [SheetLibrary Project](https://github.com/KDominikk00/SheetLibrary-app). This REST API is built using Spring Boot and provides endpoints for managing a library of sheet music. The application uses Spring Boot, JPA Hibernate, and OAuth for authentication.

## Features

- **CRUD Operations:** Perform Create, Read, Update, and Delete operations on sheet music entries.
- **User Authentication:** Secure your API with OAuth2, ensuring only authenticated users can modify data.
- **Database Integration:** Uses JPA Hibernate to interact with a PostgreSQL database.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 21 or higher
- Maven
- PostgreSQL (or another compatible SQL database)

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/KDominikk00/SheetLibrary-REST-API.git
   cd SheetLibrary-REST-API
   ```

2. **Configure the Database:**

   Update the `application.properties` or `application.yml` file in `src/main/resources` with your database credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. **Build and Run the Application:**

   Use Maven to build the project and run it:

   ```bash
   ./mvnw clean package
   java -jar target/sheets-rest-api-0.0.1-SNAPSHOT.jar
   ```

4. **Access the API:**

   Once the application is running, you can access the API at `http://localhost:8080/sheet`. You can use tools like Postman or Curl to interact with the API endpoints.

### API Overview

The API allows users to perform CRUD operations on sheet music data. It includes the following features:

- **Sheet Music Management:** Create, read, update, and delete sheet music entries.
- **User Authentication:** OAuth2-based authentication ensures that only authorized users can modify data.

### Configuration

All configuration options for the application can be adjusted via the `application.properties` or `application.yml` files in the `src/main/resources` directory. This includes settings for the database connection, security, and more.

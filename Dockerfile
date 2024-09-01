# Stage 1: Build the application using Maven
FROM maven:3.8.6-openjdk-21-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml ./
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal image with OpenJDK to run the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/sheets-rest-api-0.0.1-SNAPSHOT.jar /app/sheets-rest-api.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/sheets-rest-api.jar"]

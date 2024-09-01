# Stage 1: Build the application using Maven with Eclipse Temurin JDK 21
FROM maven:3.8.6-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Run the application using Eclipse Temurin JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/sheets-rest-api-0.0.1-SNAPSHOT.jar /app/sheets-rest-api.jar
ENTRYPOINT ["java", "-jar", "/app/sheets-rest-api.jar"]

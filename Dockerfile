# Stage 1: Build the application with Maven and JDK 21
FROM maven:3.9.3-openjdk-21 AS build

# Copy project files
COPY . /app
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image with JRE 21
FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal

# Copy the jar file from the build stage
COPY --from=build /app/target/scores-0.0.1-SNAPSHOT.jar /app/scores.jar
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "scores.jar"]

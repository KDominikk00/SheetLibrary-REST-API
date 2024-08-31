FROM maven:3.9.3-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal
COPY --from=build /target/scores-0.0.1-SNAPSHOT.jar scores.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scores.jar"]

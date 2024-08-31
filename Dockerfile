FROM maven:3.9.3-eclipse-temurin-17 AS build

# Install JDK 21
RUN apt-get update && apt-get install -y wget && \
    wget https://download.java.net/java/GA/jdk21/latest/binaries/openjdk-21_linux-x64_bin.tar.gz && \
    tar -xzf openjdk-21_linux-x64_bin.tar.gz && \
    mv jdk-21 /usr/local/ && \
    update-alternatives --install /usr/bin/java java /usr/local/jdk-21/bin/java 1 && \
    update-alternatives --install /usr/bin/javac javac /usr/local/jdk-21/bin/javac 1 && \
    update-alternatives --set java /usr/local/jdk-21/bin/java && \
    update-alternatives --set javac /usr/local/jdk-21/bin/javac

# Verify the installation
RUN java -version
RUN javac -version

# Proceed with building the project
COPY . .
RUN mvn clean package -DskipTests

# Use a minimal JRE image for the runtime stage
FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal
COPY --from=build /target/scores-0.0.1-SNAPSHOT.jar scores.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scores.jar"]

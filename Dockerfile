# Use OpenJDK 21 as the base image
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application (this will create the JAR inside the container)
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime for running the app
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/libraryManagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

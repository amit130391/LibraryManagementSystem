# Use Eclipse Temurin (Java 21) as the base image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper and necessary files
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Give execute permissions to mvnw
RUN chmod +x mvnw

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the final image
COPY target/libraryManagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

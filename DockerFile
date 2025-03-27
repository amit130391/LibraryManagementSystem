# Use a lightweight Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Give execute permission to the Maven wrapper and build the project
RUN chmod +x mvnw && ./mvnw clean package

# Set the command to run the JAR file after building
CMD ["java", "-jar", "target/*.jar"]

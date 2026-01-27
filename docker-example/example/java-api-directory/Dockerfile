# Use a base image with the Java runtime
#FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk-jammy

# Set the internal working directory
WORKDIR /app

# Copy the JAR file built by Gradle into the container
# This bundles the "code" and "libraries" into the package [1, 5]
#COPY build/libs/java-api-directory-0.0.1-SNAPSHOT.jar app.jar
COPY build/libs/app.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
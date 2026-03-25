# Use Maven with OpenJDK 11 as base image
FROM maven:3.8-openjdk-11

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Download dependencies (this layer will be cached)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Set default command to run tests
CMD ["mvn", "clean", "verify"]

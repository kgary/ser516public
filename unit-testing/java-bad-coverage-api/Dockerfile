# ---- Multi-stage Docker build for java-bad-coverage-api ----
# Stage 1: Build the fat JAR using Maven inside the container.
# Stage 2: Package the JAR into a slim JRE runtime image.

# ====== Stage 1: Build ======
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /build

# Copy dependency descriptors first for better layer caching
COPY pom.xml .
COPY .mvn/ .mvn/ 2>/dev/null || true
COPY mvnw* ./ 2>/dev/null || true

# Copy full source
COPY src ./src

# Build fat JAR, skip tests (tests run earlier in the pipeline)
RUN apk add --no-cache maven && \
    mvn package -DskipTests --no-transfer-progress

# ====== Stage 2: Runtime ======
FROM eclipse-temurin:17-jre-alpine

LABEL maintainer="SER-516 Team"
LABEL description="Calculator REST API – java-bad-coverage-api"

WORKDIR /app

# Install curl for health-check and sanity-check script
RUN apk add --no-cache curl

# Copy built JAR from builder stage
COPY --from=builder /build/target/calculator-api-1.0-SNAPSHOT.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Health check – validates the API is responding
HEALTHCHECK --interval=15s --timeout=5s --start-period=30s --retries=3 \
  CMD curl -sf -X POST http://localhost:8080/api/calculator/calculate \
      -H 'Content-Type: application/json' \
      -d '{"a":1,"b":1,"operation":"+"}' || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]

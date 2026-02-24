# ---- Docker image for java-bad-coverage Calculator Demo ----
# The JAR is already built by the Jenkins pipeline (mvn package).
# This Dockerfile simply packages it into a lightweight runtime image
# with a sanity-check entrypoint that proves the JAR is functional.

FROM eclipse-temurin:11-jdk

LABEL maintainer="SER-516 Team"
LABEL description="Calculator Demo – java-bad-coverage"

WORKDIR /app

# Copy the pre-built JAR produced by 'mvn package -DskipTests'
COPY target/calculator-demo-1.0-SNAPSHOT.jar /app/calculator-demo.jar

# Copy and compile a tiny sanity-check program that exercises the Calculator
COPY SanityCheck.java /app/SanityCheck.java
RUN javac -cp /app/calculator-demo.jar /app/SanityCheck.java

ENTRYPOINT ["java", "-cp", "/app:/app/calculator-demo.jar", "SanityCheck"]

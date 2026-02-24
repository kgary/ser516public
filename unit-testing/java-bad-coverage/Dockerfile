# ---- Docker image for java-bad-coverage Calculator Demo ----
# The JAR is already built by the Jenkins pipeline (mvn package).
# This Dockerfile simply packages it into a lightweight runtime image
# with a sanity-check entrypoint that proves the JAR is functional.

FROM openjdk:11-jre-slim

LABEL maintainer="SER-516 Team"
LABEL description="Calculator Demo – java-bad-coverage"

WORKDIR /app

# Copy the pre-built JAR produced by 'mvn package -DskipTests'
COPY target/calculator-demo-1.0-SNAPSHOT.jar /app/calculator-demo.jar

# Copy and compile a tiny sanity-check program that exercises the Calculator
COPY SanityCheck.java /app/SanityCheck.java

# We need the JDK tools (javac) to compile the sanity check.
# openjdk:11-jre-slim doesn't have javac, so we install it temporarily.
RUN apt-get update && \
    apt-get install -y --no-install-recommends default-jdk && \
    javac -cp /app/calculator-demo.jar /app/SanityCheck.java && \
    apt-get remove -y default-jdk && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java", "-cp", "/app:/app/calculator-demo.jar", "SanityCheck"]

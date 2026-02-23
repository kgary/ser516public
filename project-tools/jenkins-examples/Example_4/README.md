# Example 4 - Docker + Jenkins Demo (Java)

This example is a small Java app packaged with Maven and containerized with Docker.
It includes a Jenkins pipeline for build, test, image build, and a smoke run.

## Prerequisites

- Java 11+ (or use Docker)
- Maven 3.8+
- Docker (for container run)

## Project Layout

- src/main/java - application source
- src/test/java - unit tests (JUnit 5)
- Dockerfile - container image build
- Jenkinsfile - CI pipeline for Jenkins

## Build and Test (Local)

From this folder:

```bash
mvn -B test
mvn -B package
```

## Run Locally (No Docker)

```bash
mvn -B package
java -jar target/docker-jenkins-demo-1.0.0.jar
```

## Build and Run with Docker

```bash
docker build -t ser516-demo:local .
docker run --rm ser516-demo:local
```

The Docker build compiles the app inside the image, so you do not need a local `target/` folder.

## Run with Docker Compose

```bash
docker compose up --build
```

The container prints a greeting and then exits.

## Jenkins Pipeline Stages

The Jenkinsfile defines a 3-stage CI/CD pipeline:

1. **Build and Test** - Compiles the Java code with Maven, runs unit tests, and packages the JAR file.

2. **Docker Build** - Creates a Docker image using the multi-stage Dockerfile. The image is tagged with the Jenkins build number (e.g., `ser516-demo:42`).

3. **Docker Smoke Test** - Runs the containerized application to verify it works correctly. If the app exits with an error, the pipeline fails.

This demonstrates a complete CI workflow: compile → test → containerize → verify.

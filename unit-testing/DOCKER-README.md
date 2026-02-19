# Docker Examples for Unit Testing Projects

This directory contains Docker examples for all unit-testing projects that can run in Jenkins pipelines.


### Java Projects
- `java-bad-coverage/`: Java calculator with intentionally poor test coverage
- `java-good-coverage/`: Java calculator with comprehensive test coverage

### Python Projects
- `python-bad-coverage/`: Python calculator with intentionally poor test coverage
- `python-good-coverage/`: Python calculator with comprehensive test coverage

## Running Locally with Docker

### Option 1: Using Docker Compose (Recommended)

Docker Compose makes it easy to run the projects with a single command.

#### Java Projects
```bash
cd java-bad-coverage  # or java-good-coverage
docker-compose up
```

#### Python Projects
```bash
cd python-bad-coverage  # or python-good-coverage

# Run unit tests
docker-compose up calculator-python

# Run BDD tests
docker-compose up calculator-python-bdd

# Run both
docker-compose up
```

### Option 2: Using Docker CLI

#### Java Projects

Build the Docker image:
```bash
cd java-bad-coverage  # or java-good-coverage
docker build -t calculator-java .
```

Run tests:
```bash
docker run --rm calculator-java mvn clean verify
```

Run with volume mapping (for development):
```bash
docker run --rm -v $(pwd):/app -v ~/.m2:/root/.m2 calculator-java mvn clean verify
```

#### Python Projects

Build the Docker image:
```bash
cd python-bad-coverage  # or python-good-coverage
docker build -t calculator-python .
```

Run tests:
```bash
docker run --rm calculator-python
```

Run with volume mapping (for development):
```bash
docker run --rm -v $(pwd):/app calculator-python poetry run pytest
```

Run BDD tests:
```bash
docker run --rm -v $(pwd):/app calculator-python poetry run behave
```

## Running in Jenkins

The Jenkinsfiles are configured to automatically use Docker. Jenkins will:

1. **Build the Docker image** from the Dockerfile in each project directory
2. **Run all pipeline stages** inside the Docker container
3. **Publish test results and artifacts** after the build completes


### Jenkins Configuration Requirements

1. **Docker Support**: Ensure Jenkins has Docker installed and configured
2. **Permissions**: Jenkins user must have permission to run Docker commands
3. **Plugins Required**:
   - Docker Pipeline Plugin
   - JUnit Plugin
   - HTML Publisher Plugin
   - Code Coverage API Plugin


## Troubleshooting

### Docker Build Fails
- Ensure Docker is running: `docker ps`
- Check Docker daemon logs: `docker system df`
- Clean up unused images: `docker system prune`

### Maven Dependency Issues (Java)
- Clear Maven cache: `docker run --rm -v ~/.m2:/root/.m2 calculator-java mvn clean`
- Rebuild without cache: `docker build --no-cache -t calculator-java .`

### Poetry Lock Issues (Python)
- Update dependencies: `docker run --rm calculator-python poetry update`
- Regenerate lock file: `docker run --rm calculator-python poetry lock --no-update`

### Jenkins Docker Permission Issues
- Add Jenkins user to docker group: `sudo usermod -aG docker jenkins`
- Restart Jenkins: `sudo systemctl restart jenkins`

## Testing the Setup

### Quick Test (Java)
```bash
cd java-bad-coverage
docker build -t test-java . && docker run --rm test-java mvn clean test
```

### Quick Test (Python)
```bash
cd python-bad-coverage
docker build -t test-python . && docker run --rm test-python poetry run pytest
```

If these commands succeed, your Docker setup is working correctly!

# Docker Examples - Quick Start Guide

This guide provides a quick overview of the Docker examples created for the unit-testing projects.

### Java Projects

```bash
# Navigate to project directory
cd java-bad-coverage  # or java-good-coverage

# Run with docker-compose (easiest)
docker-compose up

# Or build and run with Docker
docker build -t calculator-java .
docker run --rm calculator-java
```

### Python Projects

```bash
# Navigate to project directory
cd python-bad-coverage  # or python-good-coverage

# Run unit tests
docker-compose up calculator-python

# Run BDD tests
docker-compose up calculator-python-bdd

# Run all tests
docker-compose up
```

## Jenkins Usage

The Jenkinsfiles are already configured to use Docker automatically. Simply:

1. Push your code to the repository
2. Jenkins will detect the Dockerfile
3. Build the Docker image
4. Run all tests inside the container
5. Publish results and artifacts


## Requirements

- Docker installed and running
- Docker Compose (optional, but recommended)
- For Jenkins: Docker Pipeline Plugin

## Troubleshooting

### Docker not running
```bash
# Check Docker status
docker ps

# Start Docker Desktop (macOS) or service (Linux)
sudo systemctl start docker
```

### Permission issues (Linux)
```bash
# Add user to docker group
sudo usermod -aG docker $USER
newgrp docker
```

### Build failures
```bash
# Clean up old images and containers
docker system prune -a

# Rebuild from scratch
docker-compose build --no-cache
docker-compose up
```

## Next Steps

1. Read [DOCKER-README.md](DOCKER-README.md) for detailed information
2. Try running the examples locally with `docker-compose up`
3. Commit changes and watch Jenkins build automatically
4. Customize Dockerfiles for your specific needs

## Support

For detailed documentation, troubleshooting, and advanced usage, see [DOCKER-README.md](DOCKER-README.md).

# Use Python 3 as base image
FROM python:3.11-slim

# Set working directory
WORKDIR /app

# Install poetry
RUN pip install --no-cache-dir poetry==1.6.1

# Copy dependency files
COPY pyproject.toml .

# Configure poetry and install dependencies
RUN poetry config virtualenvs.in-project true && \
    poetry install --no-interaction --no-ansi

# Copy source code and tests
COPY src ./src
COPY tests ./tests
COPY features ./features

# Set default command to run tests
CMD ["poetry", "run", "pytest", "--verbose"]

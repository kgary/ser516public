# ---- Docker image for python-good-coverage Calculator Demo ----
# Self-contained image: installs Poetry, project dependencies,
# and runs a sanity-check script as the entrypoint.

FROM python:3

LABEL maintainer="SER-516 Team"
LABEL description="Calculator Demo – python-good-coverage"

WORKDIR /app

# Copy project files needed for dependency installation
COPY pyproject.toml README.md /app/

# Copy the source code
COPY src/ /app/src/

# Install Poetry and project dependencies
RUN pip install poetry==1.6.1 && \
    poetry config virtualenvs.in-project true && \
    poetry install --no-interaction --no-ansi --only main

# Copy the sanity-check script
COPY sanity_check.py /app/sanity_check.py

ENTRYPOINT ["poetry", "run", "python", "sanity_check.py"]

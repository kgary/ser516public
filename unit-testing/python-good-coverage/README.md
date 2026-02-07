# Calculator Demo - Python Version

This is a Python implementation of a simple calculator application demonstrating good code coverage practices for undergraduate software engineering students.

## Features

- Basic arithmetic operations (add, subtract, multiply, divide)
- Advanced operations (power, square root)
- Comprehensive unit tests with pytest
- BDD tests with Behave
- Code coverage with Coverage.py
- Code quality checks with Pylint
- CI/CD with Jenkins

## Tool Equivalents

This Python project uses the following tools equivalent to the Java version:

| Java Tool | Python Tool | Purpose |
|-----------|-------------|---------|
| Maven | Poetry | Dependency management and build tool |
| JUnit | pytest | Unit testing framework |
| Cucumber | Behave | BDD testing framework |
| Jacoco | Coverage.py | Code coverage measurement |
| SpotBugs | Pylint | Static code analysis |

## Project Structure

```
python-code/
├── src/
│   └── calculator/
│       ├── __init__.py
│       └── calculator.py
├── tests/
│   ├── __init__.py
│   └── test_calculator.py
├── features/
│   ├── calculator.feature
│   └── steps/
│       └── calculator_steps.py
├── pyproject.toml
├── .coveragerc
├── .pylintrc
├── Jenkinsfile
└── README.md
```

## Setup

1. Install Poetry (if not already installed):
   ```bash
   curl -sSL https://install.python-poetry.org | python3 -
   ```

2. Install dependencies:
   ```bash
   cd python-code
   poetry install
   ```

## Running Tests

### Run pytest unit tests with coverage:
```bash
poetry run pytest
```

### Run Behave BDD tests:
```bash
poetry run behave
```

### Generate coverage report:
```bash
poetry run coverage run -m pytest
poetry run coverage report
poetry run coverage html
```

### Run Pylint:
```bash
poetry run pylint src/calculator
```

## Coverage Goals

This project demonstrates good code coverage practices with:
- **90%+** line coverage
- Test cases for normal operations
- Test cases for edge cases and exceptions
- Both unit tests (pytest) and BDD tests (behave)

## Jenkins Integration

The project includes a Jenkinsfile for CI/CD pipeline that:
1. Sets up Python environment
2. Installs dependencies
3. Runs pylint for code quality
4. Runs pytest with coverage
5. Runs behave tests
6. Publishes coverage reports
7. Archives test results

## Learning Objectives

Students will learn:
- How to write comprehensive test cases
- Importance of code coverage
- BDD testing approach
- CI/CD pipeline integration
- Python project structure best practices

# Calculator Demo - Comprehensive Test Coverage Example

**SUCCESS: This branch demonstrates GOOD testing practices and COMPREHENSIVE code coverage.**

This project demonstrates what happens when a Python application has excellent test coverage. You'll see how Poetry, pytest, Behave, Coverage.py, Pylint, and Jenkins work together in a CI/CD pipeline, and more importantly, you'll observe the benefits of comprehensive testing.

## Project Overview

This Calculator application implements basic arithmetic operations (add, subtract, multiply, divide, power, square root), and **all functionality is thoroughly tested**. This branch demonstrates best practices to help you understand:
- Why comprehensive testing is essential
- How to achieve high code coverage
- The confidence that comes with well-tested code

## Project Structure

```
python-good-coverage/
├── Jenkinsfile                 # Jenkins pipeline configuration
├── pyproject.toml             # Poetry project configuration
├── README.md                  # This file
├── src/
│   └── calculator/
│       ├── __init__.py
│       └── calculator.py      # Main application code
├── tests/
│   ├── __init__.py
│   └── test_calculator.py     # pytest unit tests
└── features/
    ├── calculator.feature     # Behave/Gherkin scenarios
    └── steps/
        └── calculator_steps.py # Behave step definitions
```

## Tools Overview

This project integrates six key tools that work together in the CI/CD pipeline:

- **Poetry**: Dependency management and build tool that handles virtual environments, dependencies, and project packaging
- **pytest**: Unit testing framework for writing and running individual test cases to verify code correctness
- **Behave**: Behavior-Driven Development (BDD) tool that enables writing human-readable test scenarios in Gherkin syntax
- **Coverage.py**: Code coverage tool that measures how much of your code is executed by tests, generating detailed reports
- **Pylint**: Static code analysis tool that identifies potential bugs, code smells, and PEP 8 violations without running the code
- **Jenkins**: Automation server that orchestrates the entire CI/CD pipeline, running all tools automatically on code changes

## Tool Equivalents

This Python project uses the following tools equivalent to the Java version:

| Java Tool | Python Tool | Purpose |
|-----------|-------------|---------|
| Maven | Poetry | Dependency management and build tool |
| JUnit | pytest | Unit testing framework |
| Cucumber | Behave | BDD testing framework |
| Jacoco | Coverage.py | Code coverage measurement |
| SpotBugs | Pylint | Static code analysis |

## Step-by-Step Walkthrough

### Step 1: Clone and Navigate to the Project

```bash
git clone https://github.com/kgary/ser516public
cd ser516public/unit-testing/python-good-coverage
```

### Step 2: Set Up Python Environment with Poetry

Poetry will create a virtual environment and install all dependencies.

```bash
pip install poetry
poetry install
```

**What happens:**
- Poetry reads dependencies from `pyproject.toml`
- Creates a virtual environment in `.venv/`
- Installs pytest, behave, coverage, pylint, and other dependencies

**Expected output:** Virtual environment created with all dependencies installed

### Step 3: Run Unit Tests with pytest

**EXCELLENT:** Comprehensive pytest tests with full coverage.

```bash
poetry run pytest --verbose --cov=src/calculator --cov-report=term-missing
```

**What happens:**
- pytest discovers test classes from `tests/`
- **10 comprehensive test methods** exist in `test_calculator.py`
- All Calculator methods are thoroughly tested
- Exception handling is properly tested

**Expected output:** Test results showing `Tests run: 10, Passed: 10` with HIGH coverage (90%+)

**What's Included:**
- Tests for all basic operations (add, subtract, multiply, divide)
- Edge case testing (divide by zero, negative square root, zero exponent)
- Tests for advanced operations (power, square_root)
- Exception handling tests (ArithmeticError, ValueError)
- See comprehensive tests in [test_calculator.py](tests/test_calculator.py)

### Step 4: Run BDD Tests with Behave
**EXCELLENT:** Complete Behave test scenarios that match the comprehensive unit tests.

The Behave tests are comprehensive with **8 scenarios** defined in `calculator.feature`.

```bash
poetry run behave --format=pretty
```

**What happens:**
- Behave reads scenarios from `features/calculator.feature`
- All scenarios are defined (add, subtract, multiply, divide, power, square root, error handling)
- Tests verify behavior from a user perspective
- Step definitions in `features/steps/calculator_steps.py` implement each scenario

**Example Scenario:**
```gherkin
Scenario: Add two numbers
  Given I have a calculator
  When I add 5 and 3
  Then the result should be 8.0
```

**Key Point:**
- BDD tests provide **integration-level** coverage
- Unit tests provide **10 comprehensive test methods** for detailed coverage
- Both test layers work together for complete confidencest unit tests exist
- Both test layers are needed for comprehensive coverage

### Step 5: Generate Code Coverage Report with Coverage.py

**EXCELLENT:** This is where you'll see the benefits of comprehensive testing.

Coverage.py measures which lines of code are executed during unit tests.

```bash
poetry run pytest --cov=src/calculator --cov-report=html:htmlcov --cov-report=term-missing
```

**What happens:**
- pytest runs with coverage.py integration
- Analyzes test execution data
- Generates an HTML report showing **HIGH coverage percentages**
- Most code will be highlighted in **GREEN** (tested)

**View the report:**
Open `htmlcov/index.html` in your browser to see:
- **High line coverage** (90%+) for the Calculator class
- **Complete branch coverage** for conditional logic
- Large **GREEN sections** indicating well-tested code paths

**What You'll See:**
- All methods (add, subtract, multiply, divide, power, square_root) fully tested (GREEN)
- Exception handling paths executed and verified (GREEN)
- Coverage percentage **MEETS professional standards** (90%+)
- Note: `pyproject.toml` sets `--cov-fail-under=90` (professional HIGH threshold)
- Build will **FAIL** if coverage drops below 90%, protecting code quality

### Step 6: Run Static Code Analysis with Pylint

Pylint examines source code to find potential bugs, style issues, and code quality problems.

```bash
poetry run pylint src/calculator
```

**What happens:**
- Pylint analyzes Python source code in `src/calculator/`
- Identifies PEP 8 style violations, code smells, potential bugs
- Generates a report with a code quality score (0-10)
- Checks for things like unused variables, missing docstrings, complexity

**Expected output:**
- Code quality score (e.g., "Your code has been rated at 10.00/10")
- List of issues found (if any)
- Suggestions for improvements

### Step 7: Run Everything Together

Poetry can execute the entire test pipeline in one command:

```bash
poetry run pytest --verbose --cov=src/calculator --cov-report=html:htmlcov --cov-report=term-missing && poetry run behave && poetry run pylint src/calculator
```

This simulates what Jenkins will do automatically.

## Understanding the Jenkins Pipeline

The `Jenkinsfile` defines a declarative pipeline with the following stages:

### Pipeline Stages

1. **Checkout**: Retrieves the latest code from the Git repository
2. **Install Dependencies**: Installs Poetry and project dependencies (`poetry install`)
3. **Test**: Runs pytest unit tests with coverage (`poetry run pytest`)
4. **BDD Tests**: Runs Behave scenarios (`poetry run behave`)
5. **Pylint Analysis**: Performs static code analysis (`poetry run pylint`)

### Post-Build Actions

After all stages complete, Jenkins automatically:
- Publishes pytest test results (JUnit XML format)
- Publishes Coverage.py HTML reports with trend graphs
- Archives Pylint reports for review
- Displays coverage percentage on build dashboard

### Viewing Jenkins Build Results

After a build completes, you can view:
- **Test Results**: Click "Test Results" to see which tests passed/failed
- **Coverage Trend**: View coverage percentages over time
- **HTML Reports**: Access coverage and Pylint reports
- **Console Output**: See detailed logs of each stage

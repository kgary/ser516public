# Calculator Demo - Insufficient Test Coverage Example

**WARNING: This branch demonstrates POOR testing practices and INSUFFICIENT code coverage.**

This project demonstrates what happens when a Java application lacks adequate test coverage. You'll see how Maven, JUnit, Cucumber, Jacoco, SpotBugs, and Jenkins work together in a CI/CD pipeline, but more importantly, you'll observe the consequences of incomplete testing.

## Project Overview

This Calculator application implements basic arithmetic operations (add, subtract, multiply, divide, power, square root), but **only a fraction of the code is tested**. This branch intentionally demonstrates bad practices to help you understand:
- Why comprehensive testing matters
- How to identify gaps in test coverage
- The risks of deploying inadequately tested code

## Project Structure

```
java-bad-coverage/
├── Jenkinsfile                 # Jenkins pipeline configuration
├── pom.xml                     # Maven project configuration
├── README.md                   # This file
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── calculator/
    │               └── Calculator.java          # Main application code
    └── test/
        ├── java/
        │   └── com/
        │       └── calculator/
        │           ├── CalculatorTest.java      # JUnit unit tests
        │           ├── RunCucumberTest.java     # Cucumber test runner
        │           └── steps/
        │               └── CalculatorSteps.java # Cucumber step definitions
        └── resources/
            └── features/
                └── calculator.feature           # Cucumber/Gherkin scenarios
```

## Tools Overview

This project integrates six key tools that work together in the CI/CD pipeline:

- **Maven**: Build automation tool that manages dependencies, compiles code, runs tests, and packages the application
- **JUnit 5**: Unit testing framework for writing and running individual test cases to verify code correctness
- **Cucumber**: Behavior-Driven Development (BDD) tool that enables writing human-readable test scenarios in Gherkin syntax
- **Jacoco**: Code coverage tool that measures how much of your code is executed by tests, generating detailed reports
- **SpotBugs**: Static code analysis tool that identifies potential bugs, code smells, and security vulnerabilities without running the code
- **Jenkins**: Automation server that orchestrates the entire CI/CD pipeline, running all tools automatically on code changes

## Step-by-Step Walkthrough

### Step 1: Clone and Navigate to the Project

```bash
git clone https://github.com/kgary/ser516public
cd ser516public/unit-testing/java-bad-coverage
```

### Step 2: Build the Project with Maven

Maven will download dependencies, compile your source code, and prepare the project for testing.

```bash
mvn clean compile
```

**What happens:**
- `clean`: Removes any previous build artifacts from the `target/` directory
- `compile`: Compiles the Java source code in `src/main/java/`

**Expected output:** `BUILD SUCCESS` with compiled classes in `target/classes/`

### Step 3: Run Unit Tests with JUnit

**PROBLEM IDENTIFIED:** Only minimal JUnit tests exist.

```bash
mvn test
```

**What happens:**
- Maven compiles test classes from `src/test/java/`
- JUnit runs only **1 test method** in `CalculatorTest.java` (testAdd)
- Most Calculator methods are **NOT tested** (subtract, multiply, divide, power, square root)
- Exception handling is **NOT tested**

**Expected output:** Test results showing `Tests run: 6, Failures: 0, Errors: 0` (only 1 JUnit test + 5 Cucumber scenarios)

**What's Missing:**
- No tests for subtract, multiply, divide operations
- No edge case testing (divide by zero, negative square root)
- No tests for power and square root methods
- See commented-out tests in [CalculatorTest.java](src/test/java/com/calculator/CalculatorTest.java)

### Step 4: Run BDD Tests with Cucumber

**PROBLEM IDENTIFIED:** Incomplete Cucumber test scenarios.

The Cucumber tests run automatically with `mvn test` because `RunCucumberTest.java` is included in the Surefire plugin configuration. However, only **5 scenarios** are defined in `calculator.feature`.

**What happens:**
- Cucumber reads scenarios from `calculator.feature`
- Only basic scenarios exist (add, subtract, multiply, divide, divide by zero)
- **Missing scenarios:** power operation, square root, negative square root
- Tests verify behavior from a user perspective, but incompletely

**Example Scenario:**
```gherkin
Scenario: Add two numbers
  Given I have a calculator
  When I add 5 and 3
  Then the result should be 8.0
```

**What's Missing:**
- No scenario for power operation
- No scenario for square root operation
- No scenario for negative square root error handling

### Step 5: Generate Code Coverage Report with Jacoco

**CRITICAL:** This is where you'll see the consequences of insufficient testing.

Jacoco measures which lines of code are executed during tests.

```bash
mvn jacoco:report
```

**What happens:**
- Jacoco analyzes test execution data
- Generates an HTML report showing **LOW coverage percentages**
- Large sections of code will be highlighted in **RED** (untested)

**View the report:**
Open `target/site/jacoco/index.html` in your browser to see:
- **Low line coverage** (~40-50%) for the Calculator class
- **Missing branch coverage** for conditional logic
- Large **RED sections** indicating untested code paths

**What You'll See:**
- Power and square root methods completely untested (RED)
- Exception handling paths not executed (RED)
- Only the add method is fully covered (GREEN)
- Coverage percentage **BELOW acceptable standards** (typically need 80%+)

### Step 6: Run Static Code Analysis with SpotBugs

SpotBugs examines bytecode to find potential bugs without running the code.

```bash
mvn spotbugs:spotbugs
```

**What happens:**
- SpotBugs analyzes compiled classes in `target/`
- Identifies common bug patterns (null pointer risks, resource leaks, etc.)
- Generates reports in XML and HTML formats

**View the report:**
- HTML report: `target/spotbugs.html`
- XML report: `target/spotbugsXml.xml`

### Step 7: Run Everything Together

Maven can execute the entire pipeline in one command:

```bash
mvn clean test jacoco:report spotbugs:spotbugs
```

This simulates what Jenkins will do automatically.

## Understanding the Jenkins Pipeline

The `Jenkinsfile` defines a declarative pipeline with the following stages:

### Pipeline Stages

1. **Checkout**: Retrieves the latest code from the Git repository
2. **Build**: Compiles the source code (`mvn clean compile`)
3. **Test**: Runs all JUnit and Cucumber tests (`mvn test`)
4. **Code Coverage**: Generates Jacoco coverage reports (`mvn jacoco:report`)
5. **SpotBugs Analysis**: Performs static code analysis (`mvn spotbugs:spotbugs`)
6. **Package**: Creates a JAR file of the application (`mvn package`)

### Post-Build Actions

After all stages complete, Jenkins automatically:
- Publishes JUnit test results for easy viewing
- Records Jacoco code coverage metrics with trend graphs
- Archives SpotBugs HTML reports
- Archives the built JAR artifact

# Calculator Demo - Jenkins CI/CD Walkthrough

This project demonstrates the integration of multiple software engineering tools in a continuous integration pipeline. You'll learn how Maven, JUnit, Cucumber, Jacoco, SpotBugs, and Jenkins work together to automate building, testing, and analyzing Java applications.

## Project Overview

This is a simple Calculator application that implements basic arithmetic operations (add, subtract, multiply, divide, power, square root). The application itself is intentionally simple to keep the focus on understanding the CI/CD tooling and best practices.

## Project Structure

```
java-good-coverage/
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
cd ser516public/unit-testing/java-good-coverage
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

JUnit tests verify individual methods work correctly in isolation.

```bash
mvn test
```

**What happens:**
- Maven compiles test classes from `src/test/java/`
- JUnit runs all test methods in `CalculatorTest.java`
- Tests verify operations like addition, subtraction, division by zero, etc.

**Expected output:** Test results showing passed/failed tests (e.g., `Tests run: 8, Failures: 0, Errors: 0`)

### Step 4: Run BDD Tests with Cucumber

Cucumber tests verify behavior through human-readable scenarios defined in Gherkin.

The Cucumber tests run automatically with `mvn test` because `RunCucumberTest.java` is included in the Surefire plugin configuration. The scenarios in `calculator.feature` are executed using the step definitions in `CalculatorSteps.java`.

**What happens:**
- Cucumber reads scenarios from `calculator.feature`
- Each scenario step maps to methods in `CalculatorSteps.java`
- Tests verify behavior from a user perspective

**Example Scenario:**
```gherkin
Scenario: Add two numbers
  Given I have a calculator
  When I add 5 and 3
  Then the result should be 8.0
```

### Step 5: Generate Code Coverage Report with Jacoco

Jacoco measures which lines of code are executed during tests.

```bash
mvn jacoco:report
```

**What happens:**
- Jacoco analyzes test execution data
- Generates an HTML report showing coverage percentages
- Highlights covered (green) and uncovered (red) code

**View the report:**
Open `target/site/jacoco/index.html` in your browser to see:
- Line coverage percentages per class
- Branch coverage for conditional logic
- Detailed line-by-line coverage visualization

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

### Viewing Results in Jenkins

Once your code is pushed and Jenkins runs the pipeline:
1. Navigate to your job on swent0linux.asu.edu
2. Click on a build number to view details
3. Access:
   - **Test Results**: Click "Test Result" to see passed/failed tests
   - **Code Coverage**: View coverage trends and detailed reports
   - **SpotBugs**: Check "Workspace" for `target/spotbugs.html`
   - **Console Output**: See the full build log for debugging

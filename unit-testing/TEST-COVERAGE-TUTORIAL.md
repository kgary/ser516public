# Test Coverage Failure and Resolution Guide

## Overview

The `java-bad-coverage` and `python-bad-coverage` projects have insufficient test coverage. This guide demonstrates what happens when coverage thresholds are enforced in Jenkins, how builds fail, and how to fix them by adding test cases.

## Test Coverage Metrics

- **Line Coverage**: Percentage of code lines executed during tests
- **Branch Coverage**: Percentage of decision branches (if/else) tested
- **Method Coverage**: Percentage of methods invoked by tests

---

## Java Coverage Failure Demo

### Initial State

**Project**: `java-bad-coverage`  
**Coverage Tool**: JaCoCo  
**Current Threshold**: 30%  
**Test Status**: 1 test method (`testAdd`) out of 6+ methods in `Calculator.java`

**Untested methods**:
- `subtract(a, b)`
- `multiply(a, b)`
- `divide(a, b)`
- `power(base, exponent)`
- `squareRoot(number)`
- Additional helper methods

### Step 1: Configure Jenkins Job

Create a new Jenkins Pipeline job pointing to `unit-testing/java-bad-coverage/Jenkinsfile`.

### Step 2: Run Initial Build

Trigger the Jenkins build.

**Output**: Build succeeds but with low coverage (~44%)

Jenkins shows:
- Build Status: SUCCESS
- Coverage Report: (30% threshold passing)
    - Instruction: 40.39%
    - Branch: 30%
    - Line: 48.33%
    - Method: 50%

![Java Bad Coverage Report](unit-testing/tutorial-images/java_bad_coverage_report.png)

### Step 3: Increase Threshold to 90%

Edit `pom.xml` line 132:

```xml
<minimum>0.90</minimum>  <!-- Changed from 0.50 -->
```

Commit and push changes.

### Step 4: Trigger Build with New Threshold

Jenkins automatically triggers build (or trigger manually).

**Output**: Build FAILS

Jenkins Console Output:
```
[ERROR] Failed to execute goal org.jacoco:jacoco-maven-plugin:0.8.12:check (check) on project
        calculator-demo: Coverage checks have not been met.
[WARNING] Rule violated for bundle calculator-demo: lines covered ratio is 0.48, but expected
          minimum is 0.90
[ERROR] BUILD FAILURE
```

Jenkins shows:
- Build Status: FAILED (red)
- Coverage: 48% (below 90% threshold)

![Build Failed Report Coverage](unit-testing/tutorial-images/java_bad_coverage_failed_report.png)

### Step 5: Fix - Add Missing Tests

Add these methods to `src/test/java/com/calculator/CalculatorTest.java`:

```java
@Test
public void testSubtract() {
    Calculator calculator = new Calculator();
    int result = calculator.subtract(10, 4);
    assertEquals(6, result);
}

@Test
public void testMultiply() {
    Calculator calculator = new Calculator();
    int result = calculator.multiply(6, 7);
    assertEquals(42, result);
}

@Test
public void testDivide() {
    Calculator calculator = new Calculator();
    double result = calculator.divide(15, 3);
    assertEquals(5.0, result, 0.001);
}

@Test
public void testDivideByZero() {
    Calculator calculator = new Calculator();
    assertThrows(ArithmeticException.class, () -> {
        calculator.divide(10, 0);
    });
}

@Test
public void testPower() {
    Calculator calculator = new Calculator();
    assertEquals(8.0, calculator.power(2, 3));
}

@Test
public void testPowerZeroExponent() {
    Calculator calculator = new Calculator();
    assertEquals(1.0, calculator.power(5, 0));
}

@Test
public void testSquareRoot() {
    Calculator calculator = new Calculator();
    assertEquals(4.0, calculator.squareRoot(16));
}

@Test
public void testSquareRootOfZero() {
    Calculator calculator = new Calculator();
    assertEquals(0.0, calculator.squareRoot(0));
}

@Test
public void testSquareRootNegative() {
    Calculator calculator = new Calculator();
    assertThrows(IllegalArgumentException.class, () -> {
        calculator.squareRoot(-1);
    });
}
```

Commit and push changes.

### Step 6: Trigger Build Again

Jenkins automatically builds on commit (or trigger manually).

**Output**: Build SUCCEEDS

---

## Python Coverage Failure Demo

### Initial State

**Project**: `python-bad-coverage`  
**Coverage Tool**: Coverage.py (pytest-cov)  
**Current Threshold**: 30%  
**Test Status**: 2 test methods out of 6 methods in `calculator.py`

**Untested methods**:
- `multiply(a, b)`
- `divide(a, b)`
- `power(base, exponent)`
- `square_root(number)`

### Step 1: Configure Jenkins Job

Create a new Jenkins Pipeline job pointing to `unit-testing/python-bad-coverage/Jenkinsfile`.

### Step 2: Run Initial Build

Trigger the Jenkins build.

**Output**: Build succeeds but with low coverage (60%)

Jenkins Console Output:
```
---------- coverage: platform linux, python 3.x -----------
Name                            Stmts   Miss   Cover
-----------------------------------------------------
src/calculator/calculator.py       18     8    55.56%
src/calculator/__init__.py         2      0    100%
-----------------------------------------------------
TOTAL                              20     8    60%
```

Jenkins shows:
- Build Status: SUCCESS
- Coverage Report: 60% (30% threshold passing)

![Python Low Coverage Report](/unit-testing/tutorial-images/python_bad_coverage_report.png)

### Step 3: Increase Threshold to 90%

Edit `pyproject.toml` line 33:

```toml
"--cov-fail-under=90"  # Changed from 30
```

Commit and push changes.

### Step 4: Trigger Build with New Threshold

Jenkins automatically triggers build (or trigger manually).

**Output**: Build FAILS

Jenkins Console Output:
```
FAIL Required test coverage of 90% not reached. Total coverage: 60.00%
Build failed!
```

Jenkins shows:
- Build Status: FAILED (Red)
- Coverage Report: 60% (below 90% threshold)

![Python Build Failure](unit-testing/tutorial-images/python_bad_coverage_failed_report.png)

### Step 5: Fix - Add Missing Tests

Add these methods to `tests/test_calculator.py`:

```python
def test_multiply(self):
    """Test multiplication operation."""
    calculator = Calculator()
    result = calculator.multiply(6, 7)
    assert result == 42

def test_divide(self):
    """Test division operation."""
    calculator = Calculator()
    result = calculator.divide(15, 3)
    assert result == 5.0

def test_divide_by_zero(self):
    """Test division by zero raises error."""
    calculator = Calculator()
    with pytest.raises(ArithmeticError, match="Cannot divide by zero"):
        calculator.divide(10, 0)

def test_power(self):
    """Test power operation."""
    calculator = Calculator()
    result = calculator.power(2, 3)
    assert result == 8.0

def test_power_zero_exponent(self):
    """Test power with zero exponent."""
    calculator = Calculator()
    assert calculator.power(5, 0) == 1.0

def test_square_root(self):
    """Test square root operation."""
    calculator = Calculator()
    result = calculator.square_root(16)
    assert result == 4.0

def test_square_root_of_zero(self):
    """Test square root of zero."""
    calculator = Calculator()
    assert calculator.square_root(0) == 0.0

def test_square_root_negative(self):
    """Test square root of negative number raises error."""
    calculator = Calculator()
    with pytest.raises(ValueError, match="Cannot calculate square root of negative"):
        calculator.square_root(-1)
```

Commit and push changes.

### Step 6: Trigger Build Again

Jenkins automatically builds on commit (or trigger manually).

**Output**: Build SUCCEEDS

---

## Using the Patch File

Instead of manually adding tests, apply the patch file before committing:

```bash
# From repository root
git apply unit-testing/fix-test-coverage.patch
git add .
git commit -m "Add missing test cases for coverage"
git push
```

Jenkins will automatically trigger and the build will succeed.

See [PATCH-README.md](PATCH-README.md) for detailed instructions.

---

## Jenkins Pipeline Flow

Both projects include Jenkinsfiles that execute the following stages:

### Java Pipeline (java-bad-coverage/Jenkinsfile)
1. **Checkout** - Clone repository
2. **Build** - Compile Java code with Maven
3. **Test** - Run JUnit tests
4. **Coverage Check** - Verify threshold with JaCoCo
5. **Report** - Archive coverage reports
6. **Publish** - Display coverage in Jenkins UI

### Python Pipeline (python-bad-coverage/Jenkinsfile)
1. **Checkout** - Clone repository
2. **Setup** - Install Poetry and dependencies
3. **Test** - Run pytest with coverage
4. **Coverage Check** - Verify threshold
5. **Report** - Archive coverage reports
6. **Publish** - Display coverage in Jenkins UI

**Screenshot**: Pipeline stages visualization in Jenkins

---

## Accessing Coverage Reports in Jenkins

### Method 1: Build Artifacts
1. Navigate to Jenkins job
2. Click on build number (e.g., #10)
3. Click "Build Artifacts"
4. Download and view coverage reports

### Method 2: Jenkins Plugins
If coverage plugins are installed:
1. Navigate to Jenkins job → Latest Build
2. Click "Coverage Report" in left sidebar
3. View interactive coverage details

---

## Workflow Summary

**Typical Development Workflow:**

1. Developer modifies code or adds features
2. Developer commits and pushes to repository
3. Jenkins automatically triggers build
4. Build runs tests and checks coverage
5. If coverage < threshold → Build FAILS (red)
6. Developer reviews coverage report
7. Developer adds missing tests
8. Developer commits and pushes again
9. Jenkins triggers build again
10. Coverage meets threshold → Build SUCCEEDS (green)

---


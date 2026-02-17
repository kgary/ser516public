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
**Current Threshold**: 50%  
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

**Output**: Build succeeds but with low coverage (~28%)

Jenkins shows:
- Build Status: SUCCESS
- Coverage Report: (below 50% threshold but passing)
    - Instruction: 40.39%
    - Branch: 30%
    - Line: 48.33%
    - Method: 50%

![Screenshot: 1-java-low-coverage-report.png]

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
[ERROR] Failed to execute goal org.jacoco:jacoco-maven-plugin:0.8.12:check (check)
        on project calculator-demo: Coverage checks have not been met.
[ERROR] Rule violated for bundle calculator-demo: 
        lines covered ratio is 0.28, but expected minimum is 0.90
[ERROR] BUILD FAILURE
```

Jenkins shows:
- Build Status: FAILED (red)
- Coverage: 28% (below 90% threshold)

![Screenshot: 2-java-build-failure.png]

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

Jenkins Console Output:
```
[INFO] All coverage checks have been met.
[INFO] BUILD SUCCESS
```

Jenkins shows:
- Build Status: SUCCESS (green)
- Coverage: 95% (exceeds 90% threshold)
- JaCoCo report shows green highlighting on tested methods

![Screenshot: 3-java-green-build.png]

### Step 7: View Coverage Report in Jenkins

Navigate to Jenkins job → Latest Build → Coverage Report

**Output**: JaCoCo coverage report showing 95%+ coverage with detailed breakdowns

![Screenshot: 4-java-improved-coverage.png]

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

**Output**: Build succeeds but with low coverage (~33%)

Jenkins Console Output:
```
---------- coverage: platform linux, python 3.x -----------
Name                            Stmts   Miss  Cover
---------------------------------------------------
src/calculator/calculator.py       27     18    33%
---------------------------------------------------
TOTAL                              27     18    33%
```

Jenkins shows:
- Build Status: SUCCESS
- Coverage Report: 33% (below 30% threshold but passing)

![Screenshot: 5-python-low-coverage.png]

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
FAILED tests/test_calculator.py - Failed: total coverage: 33.33%
ERROR: InvocationError for command pytest (exited with code 1)
```

Jenkins shows:
- Build Status: FAILED (red)
- Coverage: 33% (below 90% threshold)

![Screenshot: 6-python-build-failure.png]

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

Jenkins Console Output:
```
---------- coverage: platform linux, python 3.x -----------
Name                            Stmts   Miss  Cover
---------------------------------------------------
src/calculator/calculator.py       27      1    96%
---------------------------------------------------
TOTAL                              27      1    96%

Required test coverage of 90% reached. Total coverage: 96.30%
```

Jenkins shows:
- Build Status: SUCCESS (green)
- Coverage: 96% (exceeds 90% threshold)

![Screenshot: 7-python-green-build.png]

### Step 7: View Coverage Report in Jenkins

Navigate to Jenkins job → Latest Build → Coverage Report

**Output**: Coverage report showing 96%+ coverage with detailed breakdowns

![Screenshot: 8-python-improved-coverage.png]

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

## Summary

### Java Results

| Metric | Before | After |
|--------|--------|-------|
| Test Methods | 1 | 10 |
| Line Coverage | 28% | 95% |
| Jenkins Build (90% threshold) | FAIL | PASS |

### Python Results

| Metric | Before | After |
|--------|--------|-------|
| Test Methods | 2 | 10 |
| Line Coverage | 33% | 96% |
| Jenkins Build (90% threshold) | FAIL | PASS |

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

![Screenshot: 9-jenkins-pipeline.png]

---

## Accessing Coverage Reports in Jenkins

### Method 1: Build Artifacts
1. Navigate to Jenkins job
2. Click on build number (e.g., #42)
3. Click "Build Artifacts"
4. Download and view coverage reports

### Method 2: Jenkins Plugins
If coverage plugins are installed:
1. Navigate to Jenkins job → Latest Build
2. Click "Coverage Report" in left sidebar
3. View interactive coverage details

### Method 3: Archived Reports
Coverage reports are archived in Jenkins workspace:
- **Java**: `target/site/jacoco/index.html`
- **Python**: `htmlcov/index.html`

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

## References

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [pytest-cov Documentation](https://pytest-cov.readthedocs.io/)
- [Jenkins Pipeline Documentation](https://www.jenkins.io/doc/book/pipeline/)
- [PATCH-README.md](PATCH-README.md) - Patch file usage guide

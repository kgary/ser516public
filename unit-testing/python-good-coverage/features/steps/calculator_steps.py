"""Step definitions for Calculator Behave tests."""
from behave import given, when, then
from calculator.calculator import Calculator


@given('I have a calculator')
def step_impl(context):
    """Initialize calculator and exception tracking."""
    context.calculator = Calculator()
    context.exception = None
    context.result = None


@when('I add {num1:d} and {num2:d}')
def step_impl(context, num1, num2):
    """Add two numbers."""
    context.result = context.calculator.add(num1, num2)


@when('I subtract {num2:d} from {num1:d}')
def step_impl(context, num2, num1):
    """Subtract second number from first."""
    context.result = context.calculator.subtract(num1, num2)


@when('I multiply {num1:d} and {num2:d}')
def step_impl(context, num1, num2):
    """Multiply two numbers."""
    context.result = context.calculator.multiply(num1, num2)


@when('I divide {num1:d} by {num2:d}')
def step_impl(context, num1, num2):
    """Divide first number by second."""
    try:
        context.result = context.calculator.divide(num1, num2)
    except ArithmeticError as e:
        context.exception = e


@when('I raise {base:d} to the power of {exponent:d}')
def step_impl(context, base, exponent):
    """Calculate power of a number."""
    context.result = context.calculator.power(base, exponent)


@when('I calculate the square root of {number:f}')
def step_impl(context, number):
    """Calculate square root of a number."""
    try:
        context.result = context.calculator.square_root(number)
    except ValueError as e:
        context.exception = e


@when('I calculate the square root of {number:d}')
def step_impl(context, number):
    """Calculate square root of a number (integer input)."""
    try:
        context.result = context.calculator.square_root(number)
    except ValueError as e:
        context.exception = e


@then('the result should be {expected:f}')
def step_impl(context, expected):
    """Verify the result matches expected value."""
    assert context.result is not None, "Result was not set"
    assert abs(context.result - expected) < 0.001, \
        f"Expected {expected}, but got {context.result}"


@then('an ArithmeticError should be thrown')
def step_impl(context):
    """Verify ArithmeticError was thrown."""
    assert context.exception is not None, "Expected ArithmeticError to be thrown"
    assert isinstance(context.exception, ArithmeticError), \
        f"Expected ArithmeticError, but got {type(context.exception)}"


@then('a ValueError should be thrown')
def step_impl(context):
    """Verify ValueError was thrown."""
    assert context.exception is not None, "Expected ValueError to be thrown"
    assert isinstance(context.exception, ValueError), \
        f"Expected ValueError, but got {type(context.exception)}"

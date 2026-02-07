package com.calculator.steps;

import com.calculator.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for Calculator Cucumber tests
 */
public class CalculatorSteps {

    private Calculator calculator;
    private double result;
    private Exception exception;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
        exception = null;
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer num1, Integer num2) {
        result = calculator.add(num1, num2);
    }

    @When("I subtract {int} from {int}")
    public void i_subtract_from(Integer num2, Integer num1) {
        result = calculator.subtract(num1, num2);
    }

    @When("I multiply {int} and {int}")
    public void i_multiply_and(Integer num1, Integer num2) {
        result = calculator.multiply(num1, num2);
    }

    @When("I divide {int} by {int}")
    public void i_divide_by(Integer num1, Integer num2) {
        try {
            result = calculator.divide(num1, num2);
        } catch (ArithmeticException e) {
            exception = e;
        }
    }

    @When("I raise {int} to the power of {int}")
    public void i_raise_to_the_power_of(Integer base, Integer exponent) {
        result = calculator.power(base, exponent);
    }

    @When("I calculate the square root of {double}")
    public void i_calculate_the_square_root_of(Double number) {
        try {
            result = calculator.squareRoot(number);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
    }

    @Then("the result should be {double}")
    public void the_result_should_be(Double expected) {
        assertEquals(expected, result, 0.001);
    }

    @Then("an ArithmeticException should be thrown")
    public void an_arithmetic_exception_should_be_thrown() {
        assertNotNull(exception, "Expected ArithmeticException to be thrown");
        assertTrue(exception instanceof ArithmeticException);
    }

    @Then("an IllegalArgumentException should be thrown")
    public void an_illegal_argument_exception_should_be_thrown() {
        assertNotNull(exception, "Expected IllegalArgumentException to be thrown");
        assertTrue(exception instanceof IllegalArgumentException);
    }
}

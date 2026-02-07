package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple test cases for Calculator class
 */
public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }

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
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result);
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
}

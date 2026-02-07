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

    // @Test
    // public void testSubtract() {
    //     Calculator calculator = new Calculator();
    //     int result = calculator.subtract(10, 4);
    //     assertEquals(6, result);
    // }

    // TODO: Add more tests later
}

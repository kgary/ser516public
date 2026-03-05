package com.calculator;

import org.springframework.stereotype.Service;

/**
 * Service layer that routes the operation symbol to the correct
 * Calculator method.
 *
 * Supported operations: "+" | "-" | "*" | "/"
 */
@Service
public class CalculatorService {

    private final Calculator calculator = new Calculator();

    /**
     * Performs the requested arithmetic operation.
     *
     * @param a         first operand
     * @param b         second operand
     * @param operation one of "+", "-", "*", "/"
     * @return computed result
     * @throws IllegalArgumentException for unsupported operation symbols
     * @throws ArithmeticException      for division by zero
     */
    public double calculate(double a, double b, String operation) {
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException("Operation symbol must not be empty");
        }

        return switch (operation.trim()) {
            case "+" -> calculator.add(a, b);
            case "-" -> calculator.subtract(a, b);
            case "*" -> calculator.multiply(a, b);
            case "/" -> calculator.divide(a, b); // throws ArithmeticException on b == 0
            default -> throw new IllegalArgumentException(
                    "Unknown operation: \"" + operation + "\". Supported: +, -, *, /");
        };
    }
}

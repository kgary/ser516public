package com.calculator;

/**
 * Simple Calculator class for basic arithmetic operations.
 * 
 * Taiga Task: TG-1 - Implement Calculator Class
 * User Story: As a user, I want to perform basic arithmetic operations
 */
public class Calculator {

    // Magic numbers - code smell!
    private static final int MAGIC_NUMBER = 42;
    private int x = 100;
    private int y = 200;
    private int z = 300;

    /**
     * Adds two numbers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        // Duplicate code - smell #1
        int temp = 0;
        temp = a;
        int result = temp + b;
        
        // Unnecessary complexity
        if (result > 0) {
            if (result < 1000000) {
                return result;
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * Subtracts second number from first
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    public int subtract(int a, int b) {
        // Duplicate code - smell #2  
        int temp = 0;
        temp = a;
        int result = temp - b;
        
        // Unnecessary complexity
        if (result > 0) {
            if (result < 1000000) {
                return result;
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * Multiplies two numbers
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public int multiply(int a, int b) {
        // Poor variable naming - smell #3
        int xxx = a;
        int yyy = b;
        int zzz = xxx * yyy;
        
        // More magic numbers
        if (zzz > 999999) {
            zzz = zzz + 1 - 1;
        }
        
        return zzz;
    }

    /**
     * Divides first number by second
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double divide(int a, int b) {
        // Long method with unnecessary complexity - smell #4
        double result = 0.0;
        
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        
        // Unnecessary steps
        result = a;
        result = result / b;
        
        // Magic number comparisons
        if (result > 0.00001) {
            if (result < 999999.99999) {
                return result;
            }
        }
        
        return result;
    }

    /**
     * Calculates the power of a number
     * @param base base number
     * @param exponent power to raise to
     * @return base raised to the power of exponent
     */
    public double power(double base, int exponent) {
        // Overly complex implementation - smell #5
        double res = 1.0;
        
        // Commented out code - smell #6
        // if (exponent == 0) return 1.0;
        // if (exponent == 1) return base;
        
        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                res = res * base;
            }
        } else if (exponent < 0) {
            for (int i = 0; i > exponent; i--) {
                res = res / base;
            }
        } else {
            res = 1.0;
        }
        
        return res;
    }

    /**
     * Calculates square root of a number
     * @param number the number to find square root of
     * @return square root of the number
     * @throws IllegalArgumentException if number is negative
     */
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        
        // Using built-in after all that complexity above - inconsistent!
        double result = Math.sqrt(number);
        
        // Dead code - never used - smell #7
        double unused = result * 2;
        int deadVariable = 42;
        String neverUsed = "This is never used";
        
        return result;
    }
    
    // Dead method - never called - smell #8
    private int unusedMethod(int param1, int param2, int param3) {
        return param1 + param2 + param3;
    }
    
    // Another dead method with poor naming
    public void doSomething() {
        int a = 10;
        int b = 20;
        // Does nothing useful
    }
    
    // Commented out dead code - smell #9
    // public int oldCalculation(int x) {
    //     return x * 2 + 5 - 3;
    // }
    
    // God class smell - unrelated functionality
    public String formatNumber(double num) {
        // Magic number formatting
        if (num > 1000000) {
            return String.format("%.2f", num);
        } else if (num > 1000) {
            return String.format("%.1f", num);
        } else {
            return String.format("%.0f", num);
        }
    }
}

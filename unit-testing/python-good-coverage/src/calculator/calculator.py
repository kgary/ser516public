"""
Simple Calculator class for basic arithmetic operations.

Taiga Task: TG-1 - Implement Calculator Class
User Story: As a user, I want to perform basic arithmetic operations
"""
import math


class Calculator:
    """Calculator class providing basic arithmetic operations."""

    def add(self, a: int, b: int) -> int:
        """
        Add two numbers.

        Args:
            a: First number
            b: Second number

        Returns:
            Sum of a and b
        """
        return a + b

    def subtract(self, a: int, b: int) -> int:
        """
        Subtract second number from first.

        Args:
            a: First number
            b: Second number

        Returns:
            Difference of a and b
        """
        return a - b

    def multiply(self, a: int, b: int) -> int:
        """
        Multiply two numbers.

        Args:
            a: First number
            b: Second number

        Returns:
            Product of a and b
        """
        return a * b

    def divide(self, a: int, b: int) -> float:
        """
        Divide first number by second.

        Args:
            a: Dividend
            b: Divisor

        Returns:
            Quotient of a divided by b

        Raises:
            ArithmeticError: If b is zero
        """
        if b == 0:
            raise ArithmeticError("Cannot divide by zero")
        return a / b

    def power(self, base: float, exponent: int) -> float:
        """
        Calculate the power of a number.

        Args:
            base: Base number
            exponent: Power to raise to

        Returns:
            Base raised to the power of exponent
        """
        return math.pow(base, exponent)

    def square_root(self, number: float) -> float:
        """
        Calculate square root of a number.

        Args:
            number: The number to find square root of

        Returns:
            Square root of the number

        Raises:
            ValueError: If number is negative
        """
        if number < 0:
            raise ValueError("Cannot calculate square root of negative number")
        return math.sqrt(number)

"""
Unit tests for Calculator class using pytest.
"""
import pytest
from calculator.calculator import Calculator


class TestCalculator:
    """Test cases for Calculator class."""

    def test_add(self):
        """Test addition operation."""
        calculator = Calculator()
        result = calculator.add(5, 3)
        assert result == 8

    def test_subtract(self):
        """Test subtraction operation."""
        calculator = Calculator()
        result = calculator.subtract(10, 4)
        assert result == 6

    def test_multiply(self):
        """Test multiplication operation."""
        calculator = Calculator()
        result = calculator.multiply(6, 7)
        assert result == 42

    def test_divide(self):
        """Test division operation."""
        calculator = Calculator()
        result = calculator.divide(10, 2)
        assert result == 5.0

    def test_divide_by_zero(self):
        """Test division by zero raises ArithmeticError."""
        calculator = Calculator()
        with pytest.raises(ArithmeticError):
            calculator.divide(10, 0)

    def test_power(self):
        """Test power operation."""
        calculator = Calculator()
        assert calculator.power(2, 3) == 8.0

    def test_power_zero_exponent(self):
        """Test power with zero exponent."""
        calculator = Calculator()
        assert calculator.power(5, 0) == 1.0

    def test_square_root(self):
        """Test square root operation."""
        calculator = Calculator()
        assert calculator.square_root(16) == 4.0

    def test_square_root_of_zero(self):
        """Test square root of zero."""
        calculator = Calculator()
        assert calculator.square_root(0) == 0.0

    def test_square_root_negative(self):
        """Test square root of negative number raises ValueError."""
        calculator = Calculator()
        with pytest.raises(ValueError):
            calculator.square_root(-1)

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

"""
Minimal sanity-check script.
Instantiates the Calculator, runs add(2, 3), and prints the result.
Expected output: "Result: 5"

Used by the Jenkins pipeline's "Sanity Check" stage to verify the
Docker image contains a working application.
"""
from calculator.calculator import Calculator

calc = Calculator()
result = calc.add(2, 3)
print(f"Result: {result}")

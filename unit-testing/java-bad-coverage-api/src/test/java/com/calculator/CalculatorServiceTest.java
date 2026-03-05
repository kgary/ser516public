package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculatorService.
 *
 * BAD COVERAGE: Only one test is provided intentionally to demonstrate
 * poor test coverage — a teaching artefact for SER-516.
 */
public class CalculatorServiceTest {

    private final CalculatorService service = new CalculatorService();

    @Test
    public void testAdd() {
        double result = service.calculate(5, 7, "+");
        assertEquals(12.0, result);
    }

    // TODO: add tests for -, *, /, edge cases (never done - bad coverage smell)
}

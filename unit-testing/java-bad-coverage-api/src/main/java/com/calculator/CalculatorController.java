package com.calculator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for the Calculator API.
 *
 * Endpoint:
 * POST /api/calculator/calculate
 *
 * Request body (JSON):
 * {
 * "a": <number>,
 * "b": <number>,
 * "operation": "+" | "-" | "*" | "/"
 * }
 *
 * Success response (HTTP 200):
 * { "result": <number>, "operation": "<symbol>", "error": null }
 *
 * Error response (HTTP 400):
 * { "result": 0.0, "operation": "<symbol>", "error": "<message>" }
 */
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculatorResponse> calculate(@RequestBody CalculatorRequest request) {
        try {
            double result = calculatorService.calculate(
                    request.getA(),
                    request.getB(),
                    request.getOperation());
            return ResponseEntity.ok(new CalculatorResponse(result, request.getOperation()));

        } catch (ArithmeticException | IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new CalculatorResponse(e.getMessage(), request.getOperation()));
        }
    }
}

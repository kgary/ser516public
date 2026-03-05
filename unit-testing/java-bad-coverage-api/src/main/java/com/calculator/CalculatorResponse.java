package com.calculator;

/**
 * Response DTO for the Calculator REST API.
 *
 * On success: result is populated, error is null.
 * On error: result is 0.0, error contains the message.
 */
public class CalculatorResponse {

    private double result;
    private String operation;
    private String error;

    public CalculatorResponse() {
    }

    public CalculatorResponse(double result, String operation) {
        this.result = result;
        this.operation = operation;
        this.error = null;
    }

    public CalculatorResponse(String error, String operation) {
        this.result = 0.0;
        this.operation = operation;
        this.error = error;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

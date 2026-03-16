package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.dto.QuantityDTO;

public class QuantityMeasurementEntityTest {

    private String operationType;
    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private QuantityDTO result;
    private boolean comparisonResult;
    private double scalarResult;
    private boolean error;
    private String errorMessage;

    // Constructor for comparison result
    public QuantityMeasurementEntityTest(String operationType, boolean comparisonResult) {
        this.operationType = operationType;
        this.comparisonResult = comparisonResult;
        this.error = false;
    }

    // Constructor for error
    public QuantityMeasurementEntityTest(String operationType, String errorMessage) {
        this.operationType = operationType;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    // Constructor for binary operation result
    public QuantityMeasurementEntityTest(String operationType, QuantityDTO op1, QuantityDTO op2, QuantityDTO result) {
        this.operationType = operationType;
        this.operand1 = op1;
        this.operand2 = op2;
        this.result = result;
        this.error = false;
    }

    // Constructor for scalar result (division)
    public QuantityMeasurementEntityTest(String operationType, QuantityDTO op1, QuantityDTO op2, double scalarResult) {
        this.operationType = operationType;
        this.operand1 = op1;
        this.operand2 = op2;
        this.scalarResult = scalarResult;
        this.error = false;
    }

    // Getters
    public String getOperationType() {
        return operationType;
    }

    public QuantityDTO getOperand1() {
        return operand1;
    }

    public QuantityDTO getOperand2() {
        return operand2;
    }

    public QuantityDTO getResult() {
        return result;
    }

    public boolean getComparisonResult() {
        return comparisonResult;
    }

    public double getScalarResult() {
        return scalarResult;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

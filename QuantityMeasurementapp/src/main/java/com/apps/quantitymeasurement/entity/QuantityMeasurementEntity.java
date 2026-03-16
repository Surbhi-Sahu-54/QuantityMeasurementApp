package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.dto.QuantityDTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String operationType;
    private final QuantityDTO operand1;
    private final QuantityDTO operand2;
    private final QuantityDTO result;
    private final Double scalarResult;
    private final Boolean comparisonResult;
    private final boolean error;
    private final String errorMessage;
    private final Timestamp createdAt;

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO result) {
        this(operationType, operand1, null, result, null, null, false, null);
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, QuantityDTO result) {
        this(operationType, operand1, operand2, result, null, null, false, null);
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, boolean comparisonResult) {
        this(operationType, operand1, operand2, null, null, comparisonResult, false, null);
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, Double scalarResult) {
        this(operationType, operand1, operand2, null, scalarResult, null, false, null);
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, String errorMessage) {
        this(operationType, operand1, operand2, null, null, null, true, errorMessage);
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, String errorMessage) {
        this(operationType, operand1, null, null, null, null, true, errorMessage);
    }

    private QuantityMeasurementEntity(
            String operationType,
            QuantityDTO operand1,
            QuantityDTO operand2,
            QuantityDTO result,
            Double scalarResult,
            Boolean comparisonResult,
            boolean error,
            String errorMessage
    ) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.scalarResult = scalarResult;
        this.comparisonResult = comparisonResult;
        this.error = error;
        this.errorMessage = errorMessage;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

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

    public Double getScalarResult() {
        return scalarResult;
    }

    public Boolean getComparisonResult() {
        return comparisonResult;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        if (error) {
            return "Operation=" + operationType + ", ERROR=" + errorMessage;
        }

        if (comparisonResult != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", comparisonResult=" + comparisonResult;
        }

        if (scalarResult != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", scalarResult=" + scalarResult;
        }

        if (operand2 != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", result=" + result;
        }

        return "Operation=" + operationType + ", operand1=" + operand1 + ", result=" + result;
    }
}
package com.apps.quantitymeasurement.controller;


import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public String performEquality(QuantityDTO operand1, QuantityDTO operand2) {
        return displayResult(service.compare(operand1, operand2));
    }

    public String performConversion(QuantityDTO operand1, String targetUnit) {
        return displayResult(service.convert(operand1, targetUnit));
    }

    public String performAddition(QuantityDTO operand1, QuantityDTO operand2) {
        return displayResult(service.add(operand1, operand2));
    }

    public String performAddition(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
        return displayResult(service.add(operand1, operand2, targetUnit));
    }

    public String performSubtraction(QuantityDTO operand1, QuantityDTO operand2) {
        return displayResult(service.subtract(operand1, operand2));
    }

    public String performSubtraction(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
        return displayResult(service.subtract(operand1, operand2, targetUnit));
    }

    public String performDivision(QuantityDTO operand1, QuantityDTO operand2) {
        return displayResult(service.divide(operand1, operand2));
    }

    public String displayResult(QuantityMeasurementEntity entity) {
        if (entity.hasError()) {
            return "ERROR -> " + entity.getErrorMessage();
        }

        if (entity.getComparisonResult() != null) {
            return "SUCCESS -> " + entity.getComparisonResult();
        }

        if (entity.getScalarResult() != null) {
            return "SUCCESS -> " + entity.getScalarResult();
        }

        return "SUCCESS -> " + entity.getResult();
    }
}
package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityDTO operand1, QuantityDTO operand2);

    QuantityMeasurementEntity convert(QuantityDTO operand1, String targetUnit);

    QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2);

    QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2, String targetUnit);

    QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2);

    QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2, String targetUnit);

    QuantityMeasurementEntity divide(QuantityDTO operand1, QuantityDTO operand2);
}
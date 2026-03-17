package com.apps.quantitymeasurement.model;
import com.apps.quantitymeasurement.unit.IMeasurable;

/**
 * QuantityModel
 *
 * Internal business-layer model used by the service layer.
 *
 * This model combines numeric value with strongly-typed measurable unit.
 * Unlike DTO, this class is closer to the domain/business layer.
 */
public class QuantityModel {

    private final double value;
    private final IMeasurable unit;

    public QuantityModel(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "QuantityModel{" +
                "value=" + value +
                ", unit=" + unit.getUnitName() +
                ", measurementType=" + unit.getMeasurementType() +
                '}';
    }
}
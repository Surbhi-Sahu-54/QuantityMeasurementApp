package com.apps.quantitymeasurement;
import java.util.function.Function;
public enum TemperatureUnit implements IMeasurable {
    CELSIUS {
        @Override
        public double toBase(double value) {
            return value;
        }

        @Override
        public double fromBase(double baseValue) {
            return baseValue;
        }
    },
    FAHRENHEIT {
        @Override
        public double toBase(double value) {
            return (value - 32.0) * 5.0 / 9.0;
        }

        @Override
        public double fromBase(double baseValue) {
            return (baseValue * 9.0 / 5.0) + 32.0;
        }
    },
    KELVIN {
        @Override
        public double toBase(double value) {
            return value - 273.15;
        }

        @Override
        public double fromBase(double baseValue) {
            return baseValue + 273.15;
        }
    };

    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid temperature unit: " + unitName);
    }

    @Override
    public boolean supportsAddition() {
        return false;
    }

    @Override
    public boolean supportsSubtraction() {
        return false;
    }

    @Override
    public boolean supportsDivision() {
        return false;
    }
}
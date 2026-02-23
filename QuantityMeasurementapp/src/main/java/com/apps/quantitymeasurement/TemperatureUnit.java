package com.apps.quantitymeasurement;
import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(val -> val, val -> val),
    FAHRENHEIT(val -> (val - 32) * 5/9, val -> (val * 9/5) + 32);

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    TemperatureUnit(Function<Double, Double> toBase, Function<Double, Double> fromBase) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override public double convertToBase(double v) { return toBase.apply(v); }
    @Override public double convertFromBase(double b) { return fromBase.apply(b); }
    @Override public String getCategory() { return "TEMPERATURE"; }

    // UC14: Disable Arithmetic
    @Override public boolean supportsArithmetic() { return false; }
}
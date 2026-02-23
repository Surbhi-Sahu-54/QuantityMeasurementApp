package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    @Test
    void testTemperatureEquality_CelsiusAndFahrenheit() {
        Quantity<TemperatureUnit> zeroCelsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> thirtyTwoFahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(zeroCelsius, thirtyTwoFahrenheit);
    }

    @Test
    void testTemperatureConversion() {
        Quantity<TemperatureUnit> boilingCelsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = boilingCelsius.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue());
    }

    @Test
    void testTemperatureArithmetic_ShouldThrowException() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        
        // UC14: Requirement - Arithmetic on temperature throws exception
        assertThrows(UnsupportedOperationException.class, () -> t1.add(t2));
        assertThrows(UnsupportedOperationException.class, () -> t1.divide(t2));
    }

    @Test
    void testLengthArithmetic_ShouldStillWork() {
        // Backward Compatibility check (UC13 still works)
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(2.0, feet.add(inches).getValue());
    }
}
package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void givenValidLengthConversion_ShouldReturnCorrectValue() {
        // UC10: Create a generic Quantity object and use convertTo
        Quantity<LengthUnit> feetQuantity = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feetQuantity.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.001);
    }

    @Test
    void givenValidWeightConversion_ShouldReturnCorrectValue() {
        // UC10: Works identically for Weight using the same Quantity class
        Quantity<WeightUnit> kgQuantity = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kgQuantity.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.001);
    }

    @Test
    void givenNullUnit_ShouldThrowException() {
        // UC10: Constructor handles null validation
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void givenNaNValue_ShouldThrowException() {
        // UC10: Constructor handles finite value validation
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
}
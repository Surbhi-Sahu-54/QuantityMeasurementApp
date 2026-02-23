package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    private static final double EPSILON = 0.01;

    // UC4 Equality
    @Test
    void givenTwoInchesAndFiveCm_WhenCompared_ShouldReturnEqual() {

        Quantity<LengthUnit> twoInches =
                new Quantity<>(2.0, LengthUnit.INCHES);

        Quantity<LengthUnit> fiveCm =
                new Quantity<>(5.0, LengthUnit.CENTIMETER);

        assertEquals(twoInches, fiveCm);
    }

    // UC5 Conversion
    @Test
    void givenFeet_WhenConvertedToInches_ShouldReturn12() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    // UC7 Addition
    @Test
    void givenOneFeetAndOneFeet_WhenAdded_ShouldReturnTwoFeet() {

        Quantity<LengthUnit> f1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> f2 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                f1.add(f2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // UC9 Weight Equality
    @Test
    void givenKgAndGram_WhenCompared_ShouldReturnEqual() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

}
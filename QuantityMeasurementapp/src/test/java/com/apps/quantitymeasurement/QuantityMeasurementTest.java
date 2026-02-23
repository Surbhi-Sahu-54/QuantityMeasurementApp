package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    // UC13: Centralized Logic Verification (DRY Principle)
    @Test
    void givenRefactoredLogic_ShouldPerformAdditionCorrectly() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twoInches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = oneFeet.add(twoInches, LengthUnit.INCHES);
        assertEquals(14.0, result.getValue()); // 12 + 2
    }

    // UC12 & UC13: Subtraction & Non-Commutativity
    @Test
    void givenTenFeetAndSixInches_WhenSubtracted_ShouldReturnCorrectValue() {
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(9.5, tenFeet.subtract(sixInches).getValue());
    }

    // UC12 & UC13: Division (Dimensionless Scalar)
    @Test
    void givenTwentyFourInchesAndTwoFeet_WhenDivided_ShouldReturnOne() {
        Quantity<LengthUnit> inches = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, inches.divide(feet));
    }

    // Centimeter Integration Test
    @Test
    void givenTwoInchesAndFiveCm_WhenCompared_ShouldBeEqual() {
        Quantity<LengthUnit> twoInches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> fiveCm = new Quantity<>(5.0, LengthUnit.CENTIMETER);
        assertEquals(twoInches, fiveCm);
    }

    // Error Handling Consistency (DRY Validation)
    @Test
    void givenCrossCategoryOperands_ShouldThrowExceptionForAllOperations() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        
        assertThrows(IllegalArgumentException.class, () -> ((Quantity)feet).add(kg));
        assertThrows(IllegalArgumentException.class, () -> ((Quantity)feet).subtract(kg));
        assertThrows(IllegalArgumentException.class, () -> ((Quantity)feet).divide(kg));
    }

    @Test
    void givenDivisionByZero_ShouldThrowArithmeticException() {
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> tenFeet.divide(zeroFeet));
    }
}
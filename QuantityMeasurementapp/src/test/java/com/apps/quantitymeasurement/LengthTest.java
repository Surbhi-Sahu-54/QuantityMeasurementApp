package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthTest {

    // UC1 - Same reference
    @Test
    void shouldReturnTrueWhenSameReference() {
        Length length = new Length(1, LengthUnit.FEET);
        assertTrue(length.equals(length));
    }

    // UC2 - Same value & unit
    @Test
    void shouldReturnTrueWhenSameValueAndUnit() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(1, LengthUnit.FEET);
        assertEquals(l1, l2);
    }

    // UC3 - Different value
    @Test
    void shouldReturnFalseWhenDifferentValue() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(2, LengthUnit.FEET);
        assertNotEquals(l1, l2);
    }

    // UC4 - Cross unit equality
    @Test
    void shouldReturnTrueWhenDifferentUnitsButSameLength() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    // UC5 - Conversion
    @Test
    void shouldConvertFeetToInches() {
        Length length = new Length(1, LengthUnit.FEET);
        Length result = length.convertTo(LengthUnit.INCHES);

        assertEquals(12, result.getValue(), 0.00001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // UC6 - Add same unit
    @Test
    void shouldAddTwoLengthsInSameUnit() {
        Length l1 = new Length(2, LengthUnit.FEET);
        Length l2 = new Length(3, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(5, result.getValue(), 0.00001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // UC7 - Add different units with target
    @Test
    void shouldAddDifferentUnitsAndReturnInTargetUnit() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.INCHES);

        assertEquals(24, result.getValue(), 0.00001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // UC8 - Add feet + cm
    @Test
    void shouldAddFeetAndCentimeter() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(30.48, LengthUnit.CENTIMETER);

        Length result = l1.add(l2);

        assertEquals(2, result.getValue(), 0.00001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
}
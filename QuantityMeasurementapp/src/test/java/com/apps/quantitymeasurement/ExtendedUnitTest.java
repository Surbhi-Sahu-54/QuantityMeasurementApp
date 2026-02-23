package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExtendedUnitTest {

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        Length l1 = new Length(2.54, LengthUnit.CENTIMETER);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(6.0, LengthUnit.FEET);
        Length l3 = new Length(72.0,LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
        assertTrue(l2.equals(l3));
        assertTrue(l1.equals(l3));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETER);

        assertTrue(l1.equals(l1));
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETER);

        assertFalse(l1.equals(null));
    }
}
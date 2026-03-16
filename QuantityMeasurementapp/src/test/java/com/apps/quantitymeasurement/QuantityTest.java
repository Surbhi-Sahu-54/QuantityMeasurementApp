package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void givenNullUnit_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(10, null));
    }

    @Test
    void givenInfiniteValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void givenNaNValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void givenSameFeet_ShouldReturnTrue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(1, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void givenFeetAndInches_WhenEquivalent_ShouldReturnTrue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void givenYardAndInches_WhenEquivalent_ShouldReturnTrue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.YARDS);
        Quantity<LengthUnit> l2 = new Quantity<>(36, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void givenCentimeterAndInches_WhenEquivalent_ShouldReturnTrue() {
        Quantity<LengthUnit> l1 = new Quantity<>(100, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> l2 = new Quantity<>(39.3701, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void givenDifferentCategories_ShouldReturnFalse() {
        Quantity<LengthUnit> length = new Quantity<>(1, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    void givenFeet_WhenConvertedToInches_ShouldReturn12() {
        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);
        assertEquals(12, result.getValue(), 0.00001);
    }

    @Test
    void givenKg_WhenConvertedToGram_ShouldReturn1000() {
        Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000, result.getValue(), 0.00001);
    }

    @Test
    void givenFeetAndInches_WhenAdded_ShouldReturn24Inches() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2, LengthUnit.INCHES);
        assertEquals(24, result.getValue(), 0.00001);
    }

    @Test
    void givenFeetAndInches_WhenAdded_ShouldReturn2Feet() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2, LengthUnit.FEET);
        assertEquals(2, result.getValue(), 0.00001);
    }

    @Test
    void givenKgAndGram_WhenAdded_ShouldReturn2Kg() {
        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        Quantity<WeightUnit> result = w1.add(w2, WeightUnit.KILOGRAM);
        assertEquals(2, result.getValue(), 0.00001);
    }

    @Test
    void givenDifferentCategories_WhenAdded_ShouldThrowException() {
        Quantity<LengthUnit> length = new Quantity<>(1, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.add((Quantity) weight, LengthUnit.FEET));
    }
}
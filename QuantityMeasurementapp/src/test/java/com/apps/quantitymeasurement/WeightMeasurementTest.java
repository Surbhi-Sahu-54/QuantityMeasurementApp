package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightMeasurementTest {

    private static final double EPSILON = 0.001;

    // Equality: 1 kg == 1000 g
    @Test
    public void testEquality_KilogramToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    // Same reference
    @Test
    public void testEquality_SameReference() {

        Quantity<WeightUnit> w =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertTrue(w.equals(w));
    }

    // Null comparison
    @Test
    public void testEquality_NullComparison() {

        Quantity<WeightUnit> w =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(w.equals(null));
    }

    // Addition test
    @Test
    public void testAddition_KilogramAndGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(500.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(gram, WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    // Negative weight test
    @Test
    public void testNegativeWeight() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(-1.0, WeightUnit.KILOGRAM)
        );
    }
}
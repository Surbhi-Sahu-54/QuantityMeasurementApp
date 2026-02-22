package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InchEquality {

    @Test
    void testEquality_SameValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);

        assertTrue(i1.equals(i2), "1.0 inch should equal 1.0 inch");
    }

    @Test
    void testEquality_DifferentValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);

        assertFalse(i1.equals(i2), "1.0 inch should not equal 2.0 inch");
    }

    @Test
    void testEquality_NullComparison() {
        Inches i1 = new Inches(1.0);

        assertFalse(i1.equals(null), "Inches should not equal null");
    }

    @Test
    void testEquality_SameReference() {
        Inches i1 = new Inches(1.0);

        assertTrue(i1.equals(i1), "Object should equal itself");
    }

    @Test
    void testEquality_DifferentType() {
        Inches i1 = new Inches(1.0);

        assertFalse(i1.equals(1.0), "Inches should not equal different type");
    }
}
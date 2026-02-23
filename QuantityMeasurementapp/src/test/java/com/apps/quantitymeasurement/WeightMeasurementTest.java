package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightMeasurementTest {

	 private static final double EPSILON = 1e-3;

	    // Equality: 1 kg == 1000 g
	    @Test
	    public void testEquality_KilogramToGram() {
	        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
	                .equals(new Weight(1000.0, WeightUnit.GRAM)));
	    }

	    // Equality: Same reference
	    @Test
	    public void testEquality_SameReference() {
	        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
	        assertTrue(w.equals(w));
	    }

	    // Equality: Null comparison
	    @Test
	    public void testEquality_NullComparison() {
	        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(null));
	    }

	    // Equality: Different type comparison
	    @Test
	    public void testEquality_IncompatibleType() {
	        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
	        assertFalse(weight.equals("NotAWeightObject"));
	    }

	    // Addition: 1 kg + 500 g = 1.5 kg
	    @Test
	    public void testAddition_KilogramAndGram() {
	        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
	        Weight w2 = new Weight(500.0, WeightUnit.GRAM);

	        Weight result = w1.add(w2);

	        assertEquals(1.5, result.getValue(), EPSILON);
	        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
	    }

	    // Negative weight should throw exception
	    @Test
	    public void testNegativeWeight_ThrowsException() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Weight(-1.0, WeightUnit.KILOGRAM);
	        });
	    }
	}
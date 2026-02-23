package com.apps.quantitymeasurement;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LengthAdditionTest {

	private static final double EPSILON = 0.0001;

	// Same Unit Addition

	@Test
	void testAddition_SameUnit_FeetPlusFeet() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(2.0, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(3.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testAddition_SameUnit_InchPlusInch() {
		Length l1 = new Length(6.0, LengthUnit.INCHES);
		Length l2 = new Length(6.0, LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(12.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	// Cross Unit Addition

	@Test
	void testAddition_CrossUnit_FeetPlusInches() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(2.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testAddition_CrossUnit_InchesPlusFeet() {
		Length l1 = new Length(12.0, LengthUnit.INCHES);
		Length l2 = new Length(1.0, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(24.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testAddition_CrossUnit_YardPlusFeet() {
		Length l1 = new Length(1.0, LengthUnit.YARDS);
		Length l2 = new Length(3.0, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(2.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.YARDS, result.getUnit());
	}

	@Test
	void testAddition_CrossUnit_CentimeterPlusInch() {
		Length l1 = new Length(2.54, LengthUnit.CENTIMETER);
		Length l2 = new Length(1.0, LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(5.08, result.getValue(), EPSILON);
		assertEquals(LengthUnit.CENTIMETER, result.getUnit());
	}

	// Commutativity

	@Test
	void testAddition_Commutativity() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		Length result1 = l1.add(l2);
		Length result2 = l2.add(l1);

		// Compare in base unit using equals
		assertTrue(result1.equals(result2));
	}

	// Identity Element

	@Test
	void testAddition_WithZero() {
		Length l1 = new Length(5.0, LengthUnit.FEET);
		Length l2 = new Length(0.0, LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(5.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	// Negative Values

	@Test
	void testAddition_NegativeValues() {
		Length l1 = new Length(5.0, LengthUnit.FEET);
		Length l2 = new Length(-2.0, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(3.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	// Null Handling

	@Test
	void testAddition_NullSecondOperand() {
		Length l1 = new Length(1.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> {
			l1.add(null);
		});
	}

	// Large Values

	@Test
	void testAddition_LargeValues() {
		Length l1 = new Length(1e6, LengthUnit.FEET);
		Length l2 = new Length(1e6, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(2e6, result.getValue(), EPSILON);
	}

	// Small Values

	@Test
	void testAddition_SmallValues() {
		Length l1 = new Length(0.001, LengthUnit.FEET);
		Length l2 = new Length(0.002, LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(0.003, result.getValue(), EPSILON);
	}
}
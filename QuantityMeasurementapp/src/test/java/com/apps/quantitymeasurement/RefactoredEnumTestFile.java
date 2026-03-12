package com.apps.quantitymeasurement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RefactoredEnumTestFile {

	private static final double EPSILON = 1e-3;

	// Quantity(1.0, FEET).convertTo(INCHES)
	@Test
	public void givenFeet_whenConvertToInches_shouldReturn12Inches() {
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length result = feet.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	// Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)
	@Test
	public void givenFeetAndInches_whenAddWithTargetFeet_shouldReturn2Feet() {
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);

		Length result = feet.add(inches, LengthUnit.FEET);

		assertEquals(2.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	// Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))
	@Test
	public void given36InchesAnd1Yard_whenEquals_shouldReturnTrue() {
		Length inches = new Length(36.0, LengthUnit.INCHES);
		Length yard = new Length(1.0, LengthUnit.YARDS);

		assertTrue(inches.equals(yard));
	}

	// Quantity(1.0, YARDS).add(Quantity(3.0, FEET), YARDS)
	@Test
	public void givenYardsAndFeet_whenAddWithTargetYards_shouldReturn2Yards() {
		Length yard = new Length(1.0, LengthUnit.YARDS);
		Length feet = new Length(3.0, LengthUnit.FEET);

		Length result = yard.add(feet, LengthUnit.YARDS);

		assertEquals(2.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.YARDS, result.getUnit());
	}

	// Quantity(2.54, CENTIMETERS).convertTo(INCHES)
	@Test
	public void givenCentimeters_whenConvertToInches_shouldReturn1Inch() {
		Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
		Length result = cm.convertTo(LengthUnit.INCHES);

		assertEquals(1.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	// Zero addition case
	@Test
	public void givenZeroInches_whenAddToFeet_shouldRemainSame() {
		Length feet = new Length(5.0, LengthUnit.FEET);
		Length zero = new Length(0.0, LengthUnit.INCHES);

		Length result = feet.add(zero, LengthUnit.FEET);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	// Negative value case
	@Test
	public void givenNegativeFeet_whenAdd_shouldReturnCorrectResult() {
		Length feet = new Length(5.0, LengthUnit.FEET);
		Length negative = new Length(-2.0, LengthUnit.FEET);

		Length result = feet.add(negative, LengthUnit.FEET);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	// Round-trip conversion stability
	@Test
	public void givenRoundTripConversion_shouldReturnOriginalValue() {
		Length original = new Length(1.0, LengthUnit.YARDS);
		Length result = original.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.YARDS);

		assertEquals(original.getValue(), result.getValue(), EPSILON);
	}
}
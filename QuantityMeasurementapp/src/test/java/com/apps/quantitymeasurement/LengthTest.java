package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthTest {
	 @Test
	    void testEquality_FeetToFeet_SameValue() {
	        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

	        assertTrue(l1.equals(l2), "1 ft should equal 1 ft");
	    }

	    @Test
	    void testEquality_InchToInch_SameValue() {
	        Length l1 = new Length(5.0, Length.LengthUnit.INCHES);
	        Length l2 = new Length(5.0, Length.LengthUnit.INCHES);

	        assertTrue(l1.equals(l2), "5 inches should equal 5 inches");
	    }

	    // ============================
	    // CROSS UNIT EQUALITY TESTS
	    // ============================

	    @Test
	    void testEquality_FeetToInches_EquivalentValue() {
	        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	        assertTrue(l1.equals(l2), "1 foot should equal 12 inches");
	    }

	    @Test
	    void testEquality_InchesToFeet_EquivalentValue() {
	        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
	        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

	        assertTrue(l1.equals(l2), "12 inches should equal 1 foot");
	    }

	    // ============================
	    // DIFFERENT VALUE TESTS
	    // ============================

	    @Test
	    void testEquality_FeetToFeet_DifferentValue() {
	        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

	        assertFalse(l1.equals(l2), "1 ft should not equal 2 ft");
	    }

	    @Test
	    void testEquality_InchesToInches_DifferentValue() {
	        Length l1 = new Length(10.0, Length.LengthUnit.INCHES);
	        Length l2 = new Length(11.0, Length.LengthUnit.INCHES);

	        assertFalse(l1.equals(l2), "10 inches should not equal 11 inches");
	    }

	    // ============================
	    // NULL & TYPE SAFETY TESTS
	    // ============================

	    @Test
	    void testEquality_NullComparison() {
	        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

	        assertFalse(l1.equals(null), "Length should not equal null");
	    }

	    @Test
	    void testEquality_DifferentType() {
	        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

	        assertFalse(l1.equals("1.0"), "Length should not equal different type");
	    }

	    // ============================
	    // SAME REFERENCE TEST
	    // ============================

	    @Test
	    void testEquality_SameReference() {
	        Length l1 = new Length(2.0, Length.LengthUnit.FEET);

	        assertTrue(l1.equals(l1), "Object should equal itself");
	    }

	    // ============================
	    // INVALID UNIT TEST
	    // ============================

	    @Test
	    void testConstructor_NullUnit() {
	        assertThrows(
	                IllegalArgumentException.class,
	                () -> new Length(1.0, null),
	                "Constructor should throw exception for null unit"
	        );
	    }

}

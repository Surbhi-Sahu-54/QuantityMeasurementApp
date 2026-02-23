package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericQuantityTest {
	
	 @Test
	    public void testLengthToLengthEquality() {
	        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
	        assertEquals(oneFeet, twelveInches);
	    }

	    @Test
	    public void testWeightToWeightEquality() {
	        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> thousandGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
	        assertEquals(oneKg, thousandGrams);
	    }

	    @Test
	    public void testCrossCategoryEquality_ShouldReturnFalse() {
	        // UC10: Prevents comparing 1 Feet to 1 Kilogram
	        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        
	        // This should return false based on the getCategory() check in Quantity.equals()
	        assertNotEquals(oneFeet, oneKg); 
	    }

	    @Test
	    public void testGenericAddition() {
	        // 1 Feet + 12 Inches = 2 Feet (Explicit target)
	        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
	        
	        Quantity<LengthUnit> sum = l1.add(l2, LengthUnit.FEET);
	        assertEquals(2.0, sum.getValue(), 0.001);
	    }

	    @Test
	    public void testConversionWithDifferentCategories_ShouldThrowException() {
	        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
	        // This is a runtime check to ensure IMeasurable categories match
	        assertThrows(IllegalArgumentException.class, () -> {
	            // Note: In actual code, the compiler might stop this if the type U is strictly bound
	            // but the add/convertTo methods in Quantity check categories internally
	            feet.add((Quantity) new Quantity<>(1.0, WeightUnit.KILOGRAM), LengthUnit.FEET);
	        });
	    }
	}


package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ---------------- GENERIC DEMONSTRATION METHODS ----------------
    // These methods work for ANY measurement category (UC10 Principle)

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        boolean result = q1.equals(q2);
        System.out.println(String.format("Equality Check: [%s] == [%s] ? -> %b", q1, q2, result));
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        Quantity<U> result = quantity.convertTo(targetUnit);
        System.out.println(String.format("Conversion: [%s] to %s -> %s", quantity, targetUnit, result));
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.println(String.format("Addition: [%s] + [%s] expressed in %s -> %s", q1, q2, targetUnit, result));
        return result;
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        // --- UC1 to UC8: Length Operations ---
        System.out.println("=== UC1 - UC8: Length Measurements ===");
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> oneYard = new Quantity<>(1.0, LengthUnit.YARDS);

        demonstrateEquality(oneFeet, twelveInches); // UC1
        demonstrateEquality(oneYard, new Quantity<>(36.0, LengthUnit.INCHES)); // UC2
        demonstrateConversion(oneFeet, LengthUnit.INCHES); // UC5
        demonstrateAddition(oneFeet, twelveInches, LengthUnit.INCHES); // UC7
        demonstrateAddition(new Quantity<>(2.0, LengthUnit.INCHES), 
                            new Quantity<>(2.5, LengthUnit.CENTIMETERS), 
                            LengthUnit.INCHES); // UC8

        // --- UC9: Weight Operations ---
        System.out.println("\n=== UC9: Weight Measurements ===");
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        
        demonstrateEquality(oneKg, thousandGrams);
        demonstrateAddition(oneKg, thousandGrams, WeightUnit.KILOGRAM);

        // --- UC11: Volume Operations ---
        System.out.println("\n=== UC11: Volume Measurements ===");
        Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litreEquiv = new Quantity<>(3.78541, VolumeUnit.LITRE);
        
        demonstrateEquality(oneGallon, litreEquiv); // Gallon to Litre
        
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> mlEquiv = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        
        demonstrateEquality(oneLitre, mlEquiv); // Litre to ML
        demonstrateAddition(oneLitre, mlEquiv, VolumeUnit.LITRE); // Addition (1L + 1000mL = 2L)
        demonstrateAddition(oneGallon, litreEquiv, VolumeUnit.LITRE); // Addition (1 Gal + 3.78L)

        // --- UC10 & UC11: Cross-Category Safety ---
        System.out.println("\n=== UC10/11: Type Safety Verification ===");
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        
        // This uses the improved equals() in Quantity.java that checks getCategory()
        System.out.println("1 Feet equals 1 Litre? -> " + length.equals(volume));
    }
}
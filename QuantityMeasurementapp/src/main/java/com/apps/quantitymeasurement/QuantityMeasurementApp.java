package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        System.out.println("=== Quantity Measurement App  ===");

        // Length Subtraction
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
        System.out.println("Subtraction (10ft - 6in): " + tenFeet.subtract(sixInches));

        // Weight Division (Fixing Line 30 Error)
        Quantity<WeightUnit> tenKg = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> fiveKg = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        
        // Note: result is a DOUBLE, not a Quantity
        double ratio = tenKg.divide(fiveKg); 
        System.out.println("Division Ratio (10kg / 5kg): " + ratio);

        // Volume Zero Check
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("Zero Result (1L - 1000mL): " + oneLitre.subtract(thousandMl));
    }
}
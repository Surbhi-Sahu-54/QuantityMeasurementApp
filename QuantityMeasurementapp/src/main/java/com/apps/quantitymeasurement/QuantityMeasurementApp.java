package com.apps.quantitymeasurement;
import java.util.Scanner;

public class QuantityMeasurementApp {
	// Demonstrate equality check
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        System.out.println("Equal (" + length1.compare(length2) + ")");
        return length1.equals(length2);
    }

    // Demonstrate comparison
    public static boolean demonstrateLengthComparison(Length length1, Length length2) {
        System.out.println("Compare (" + length1.compare(length2) + ")");
        return length1.compare(length2);
    }

    // Conversion using raw value
    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Length base = new Length(value, fromUnit);
        return base.convertTo(toUnit);
    }

    // Conversion using Length object
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    // UC6
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // UC7
    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    /**
     * Static conversion API
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        Length sourceLength = new Length(value, source);
        return sourceLength.convertTo(target).getValue();
    }

    public static void main(String[] args) {

        System.out.println("=== Comparison ===");
        demonstrateLengthComparison(new Length(1, LengthUnit.FEET),
                new Length(12, LengthUnit.INCHES));

        demonstrateLengthComparison(new Length(1, LengthUnit.YARDS),
                new Length(36, LengthUnit.INCHES));

        System.out.println("\n=== Conversion ===");
        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println(demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS));
        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(
                new Length(1.0, LengthUnit.FEET), LengthUnit.INCHES));

        System.out.println("\n=== UC6 Addition ===");
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES)));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(2.0, LengthUnit.YARDS)));

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET)));

        System.out.println("\n=== UC7 Explicit Target ===");
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.YARDS));

        System.out.println("\n=== UC8 Refactored Design ===");

        System.out.println("Convert: "
                + demonstrateLengthConversion(
                new Length(1.0, LengthUnit.FEET), LengthUnit.INCHES));

        System.out.println("Add & Convert: "
                + demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET));

        Length thirtySixInches = new Length(36.0, LengthUnit.INCHES);
        Length oneYard = new Length(1.0, LengthUnit.YARDS);

        System.out.println("Equality: "
                + thirtySixInches.equals(oneYard));

        System.out.println("Unit API Test: "
                + LengthUnit.FEET.convertToBaseUnit(12.0));

        System.out.println("Unit API Test: "
                + LengthUnit.INCHES.convertToBaseUnit(12.0));
    }
}
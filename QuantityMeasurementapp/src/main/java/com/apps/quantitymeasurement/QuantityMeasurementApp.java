package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
    boolean result = q1.equals(q2);
    System.out.println(q1 + " == " + q2 + " ? " + result);
    return result;
}

public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
    Quantity<U> result = quantity.convertTo(targetUnit);
    System.out.println("Convert " + quantity + " to " + targetUnit + " = " + result);
    return result;
}

public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
    Quantity<U> result = q1.add(q2, targetUnit);
    System.out.println(q1 + " + " + q2 + " = " + result);
    return result;
}

public static void main(String[] args) {

    System.out.println("Length Tests");

    Quantity<LengthUnit> oneFeet =
            new Quantity<>(1.0, LengthUnit.FEET);

    Quantity<LengthUnit> twelveInches =
            new Quantity<>(12.0, LengthUnit.INCHES);

    demonstrateEquality(oneFeet, twelveInches);

    demonstrateAddition(oneFeet, twelveInches, LengthUnit.INCHES);


    System.out.println("\nWeight Tests");

    Quantity<WeightUnit> oneKg =
            new Quantity<>(1.0, WeightUnit.KILOGRAM);

    Quantity<WeightUnit> thousandGram =
            new Quantity<>(1000.0, WeightUnit.GRAM);

    demonstrateEquality(oneKg, thousandGram);


    System.out.println("\nVolume Tests");

    Quantity<VolumeUnit> oneLitre =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> thousandML =
            new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    demonstrateEquality(oneLitre, thousandML);
}


}

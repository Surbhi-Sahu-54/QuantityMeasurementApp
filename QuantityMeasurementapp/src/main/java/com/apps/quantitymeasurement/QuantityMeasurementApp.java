package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Length quantities
        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + l1.equals(l2));

        Quantity<LengthUnit> l3 =
                new Quantity<>(1, LengthUnit.YARDS);

        Quantity<LengthUnit> l4 =
                new Quantity<>(36, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + l3.equals(l4));

        Quantity<LengthUnit> l5 =
                new Quantity<>(100, LengthUnit.CENTIMETERS);

        Quantity<LengthUnit> l6 =
                new Quantity<>(39.3701, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + l5.equals(l6));

        // Addition
        System.out.println("Result in YARDS: " +
                l1.add(l2, LengthUnit.YARDS));

        System.out.println("Result in INCHES: " +
                l1.add(l2, LengthUnit.INCHES));

        // Weight quantities
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Are weights equal? " + w1.equals(w2));
    }
}
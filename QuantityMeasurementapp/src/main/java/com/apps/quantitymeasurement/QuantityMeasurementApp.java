package com.apps.quantitymeasurement;
import java.util.Scanner;

public class QuantityMeasurementApp {
	 public static void main(String[] args) {

	        Scanner input = new Scanner(System.in);

	        try {
	            System.out.print("Enter first value in feet: ");
	            double first = Double.parseDouble(input.nextLine());

	            System.out.print("Enter second value in feet: ");
	            double second = Double.parseDouble(input.nextLine());

	            Feet feet1 = new Feet(first);
	            Feet feet2 = new Feet(second);

	            boolean result = feet1.equals(feet2);

	            System.out.println("Equal (" + result + ")");

	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input! Please enter numeric values only.");
	        }

	        input.close();
	    }

}

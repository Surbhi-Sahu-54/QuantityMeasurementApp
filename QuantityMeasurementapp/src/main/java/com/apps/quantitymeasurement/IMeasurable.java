package com.apps.quantitymeasurement;

public interface IMeasurable {

    double convertToBase(double value);

    double convertFromBase(double baseValue);

    String getCategory();
}
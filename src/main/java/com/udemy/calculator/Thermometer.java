package com.udemy.calculator;

public class Thermometer {

  private float result;

  public float convertTemperatureCelsiusToFahrenheit(float degree) {
    return degree * (9f / 5) + 32;
  }

  public float convertTemperatureFahrenheitToCelsius(float degree) {
    return degree - 32 * (5f / 9);
  }
}

package com.udemy.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermometerTest {

  private Thermometer thermometer;

  @BeforeEach
  void setUp() {
    thermometer = new Thermometer();
  }

  @Test
  void testConvertTemperatureCelsiusToFahrenheit() {
    assertEquals(35.6, thermometer.convertTemperatureCelsiusToFahrenheit(2), 0.01);
  }

  @Test
  void testConvertTemperatureFahrenheitToCelsius() {
    assertEquals(-16.6, thermometer.convertTemperatureFahrenheitToCelsius(2), 1.00);
  }
}
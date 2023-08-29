package com.udemy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidNumberTest {

  private ValidNumber validNumber;

  @BeforeEach
  void setUp() {
    validNumber = new ValidNumber();
  }

  @Test
  void testIsValidNumber_whenNumberIsPositive() {
    assertTrue(validNumber.isValidNumber(5));
  }

  @Test
  void testIsValidNumber_whenNumberIsNegative() {
    assertFalse(validNumber.isValidNumber(-5));
  }

  @Test
  void testIsValidNumber_whenIsStringValue() {
    assertFalse(validNumber.isValidNumber("text"));
  }

  @Test
  void testIsZeroNumber_shouldReturnAnException_whenIsZeroValue() {
    assertThrows(ArithmeticException.class, () -> validNumber.isZeroNumber(0));
  }

  @Test
  void testIsZeroNumber_whenNumberIsNegative() {
    assertTrue(validNumber.isZeroNumber(-57));
  }

  @Test
  void testIsZeroNumber_whenIsStringValue() {
    assertFalse(validNumber.isZeroNumber("5"));
  }
}

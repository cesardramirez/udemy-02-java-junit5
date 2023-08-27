package com.udemy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
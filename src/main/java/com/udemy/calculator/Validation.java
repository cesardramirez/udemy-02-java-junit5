package com.udemy.calculator;

import com.udemy.util.ValidNumber;

public class Validation {

  private ValidNumber validNumber;

  Validation(ValidNumber validNumber) {
    this.validNumber = validNumber;
  }

  int addNumbers(Object objA, Object objB) {
    if (validNumber.isValidNumber(objA) && validNumber.isValidNumber(objB)) {
      return (int) objA + (int) objB;
    }
    return 0;
  }
}

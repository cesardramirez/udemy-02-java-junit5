package com.udemy.calculator;

import com.udemy.util.ValidNumber;

public class Validation {

  private ValidNumber validNumber;
  private Print print;

  Validation(ValidNumber validNumber) {
    this.validNumber = validNumber;
  }

  Validation(ValidNumber validNumber, Print print) {
    this.validNumber = validNumber;
    this.print = print;
  }

  int addNumbers(Object objA, Object objB) {
    if (validNumber.isValidNumber(objA) && validNumber.isValidNumber(objB)) {
      return (int) objA + (int) objB;
    }
    return 0;
  }

  int numberIntegerSquared(Object obj) {
    return validNumber.doubleToInt(obj) + validNumber.doubleToInt(obj);
  }

  void addPrint(Object objA, Object objB) {
    if (validNumber.isValidNumber(objA) && validNumber.isValidNumber(objB)) {
      int result = (int) objA + (int) objB;
      print.showMessage(result);
    } else {
      print.showError();
    }
  }
}

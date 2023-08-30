package com.udemy.util;

public class ValidNumber {

  public boolean isValidNumber(Object obj) {
    if (obj instanceof Integer) {
      int number = (Integer) obj;
      return number < 10 && number >= 0;
    } else {
      return false;
    }
  }

  public boolean isZeroNumber(Object obj) {
    if (obj instanceof Integer) {
      if ((Integer) obj == 0) {
        throw new ArithmeticException("No puede ser cero.");
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  public int doubleToInt(Object obj) {
    if (obj instanceof Double) {
      return ((Double) obj).intValue();
    } else {
      return 0;
    }
  }
}

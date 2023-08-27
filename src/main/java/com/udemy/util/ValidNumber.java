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
}

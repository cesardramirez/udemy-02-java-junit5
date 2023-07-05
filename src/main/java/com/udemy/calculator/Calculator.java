package com.udemy.calculator;

public class Calculator {

  private int result;

  public int addNumbers(int num1, int num2) {
    result = num1 + num2;
    return result;
  }

  public int subtractNumbers(int num1, int num2) {
    result = num1 - num2;
    return result;
  }
}

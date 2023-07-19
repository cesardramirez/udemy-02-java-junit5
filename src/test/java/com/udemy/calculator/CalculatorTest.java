package com.udemy.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  private Calculator calculator;
  private Calculator calculatorNull;

  /**
   * Este método se ejecuta antes de cada método de prueba.
   * Instrucciones que son comunes en cada uno de los test.
   */
  @BeforeEach
  void setUp() {
    calculator = new Calculator();
    System.out.println("@BeforeEach -> setUp()");
  }

  /**
   * Este método se ejecuta después de cada método de prueba.
   * Sirve para liberar recursos (o limpieza) que se haya podido inicializar en BeforeEach
   *   (ya sea objetos instanciados o bases de datos).
   */
  @AfterEach
  void tearDown() {
    calculator = null;
    System.out.println("@AfterEach -> tearDown()");
  }

  @Test
  void testCalculatorNotNull() {
    assertNotNull(calculator, "Calculator no debe ser nulo.");

    System.out.println("@Test -> calculatorNotNullTest()");
  }

  @Test
  void testCalculatorNull() {
    assertNull(calculatorNull);

    System.out.println("@Test -> calculatorNullTest()");
  }

  @Test
  void testAddNumbersMethod() {
    // arrange
    Calculator calculatorAssert = new Calculator();

    // action
    int result = calculatorAssert.addNumbers(10, 20);

    // assert
    assertEquals(30, result);
    assertEquals(1, 1.4, 0.5);  // true, ya que 1.4 está entre 1 y 1.5.

    System.out.println("@Test -> testAddNumbersMethod()");
  }

  @Test
  void testSameObject() {
    // arrange
    Calculator calculator1 = new Calculator();
    Calculator calculator2 = new Calculator();
    Calculator calculator3 = calculator1;

    // assert
    assertSame(calculator3, calculator1);
    assertNotSame(calculator3, calculator2);
  }

  @Test
  void testSubtractNumbers_whenResultIsNegative() {
    // arrange
    Calculator calculatorAssert = new Calculator();

    // action
    int result = calculatorAssert.subtractNumbers(10, 20);

    // assert
    assertEquals(-10, result);
  }
}

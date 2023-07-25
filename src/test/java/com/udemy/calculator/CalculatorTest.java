package com.udemy.calculator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  private Calculator calculator;
  private Calculator calculatorNull;
  private static Calculator calculatorStatic;

  /**
   * Este método se ejecuta al inicio de la clase de test una única vez.
   * Es útil si se desea inicializar una única vez una base de datos o un objeto con unas características en particular.
   * Este método debe ser estático al igual que sus variables.
   */
  @BeforeAll
  static void beforeAll() {
    calculatorStatic = new Calculator();
    System.out.println("@BeforeAll -> beforeAll() \n");
  }

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
    System.out.println("@AfterEach -> tearDown() \n");
  }

  /**
   * Este método se ejecuta al final de la clase de test una única vez.
   * Es útil si se desea finalizar una única vez una base de datos o un objeto con unas características en particular.
   * Este método debe ser estático al igual que sus variables.
   */
  @AfterAll
  static void afterAll() {
    calculatorStatic = null;
    System.out.println("@afterAll -> afterAll() \n");
  }

  @Test
  void testCalculatorNotNull() {
    assertNotNull(calculatorStatic);
    assertNotNull(calculator, "Calculator no debe ser nulo.");

    System.out.println("@Test -> calculatorNotNullTest()");
  }

  @Test
  void testCalculatorNull() {
    assertNull(calculatorNull);

    System.out.println("@Test -> calculatorNullTest()");
  }

  /**
   * assertEquals(expected, actual, delta):
   * Con base al valor actual se verifica si cumple con el rango de delta esperado.
   */
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

  /**
   * assertSame() y assertNotSame():
   * Validar si un objeto es el mismo (con todos su valores) o no es el mismo.
   */
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
  void testSubtractNumbersMethod_whenResultIsNegative() {
    // arrange
    Calculator calculatorAssert = new Calculator();

    // action
    int result = calculatorAssert.subtractNumbers(10, 20);

    // assert
    assertEquals(-10, result);
  }

  /**
   * La anotación @DisplayName("message") permite cambiarle el nombre al test.
   * No es recomendable ya que el nombre del test es fácil de ubicar con el nombre de la función.
   */
  @Test
  @DisplayName("Test para el método dividir números")
  void testDivideNumbersMethod() {
    assertEquals(2, calculator.divideNumbers(10, 5));
  }

  /**
   * assertThrows(Exception.class, () -> methodToTest()):
   * Valida si se genera una excepción en particular y los valores dentro de esta.
   */
  @Test
  void shouldThrowException_whenDivideByZero() {
    Exception exception = assertThrows(ArithmeticException.class,
            () -> calculator.divideNumbers(10, 0));

    String actualMessage = exception.getMessage();
    String expectedMessage = "No se puede dividir por cero";

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * La anotación @Disabled descarma un test para que no sea ejecutado (omitido).
   */
  @Test
  @Disabled("pending bug to review")
  void testDivideNumbersMethodBug() {
    assertEquals(3, calculator.divideNumbers(10, 5));
  }

  /**
   * Comúnmente, un método test que tenga varios assert, donde falle el primero, no se ejecutará el resto.
   * Con assertAll() se ejecutan TODOS los assert indicados y el test será exitoso
   *   si todos los assert se se cumplen.
   */
  @Test
  void testAssertAllCalculator() {
    assertAll(
            () -> assertEquals(30, calculator.addNumbers(10, 20)),
            () -> assertNull(calculatorNull),
            () -> assertEquals(5, calculator.divideNumbers(10, 2))
    );
  }

  /**
   * La anotación @Nested permite agrupar un conjunto de tests, útil si un método tiene varias casuísticas.
   * Por ejemplo, si en un método en particular espero que devuelva un valor positivo, negativo o cero.
   */
  @Nested
  class TestAddNumbersMethod {
    @Test
    public void testAddNumbersMethod_whenNumbersArePositive() {
      assertEquals(30, calculator.addNumbers(15, 15));
    }

    @Test
    public void testAddNumbersMethod_whenNumbersAreNegative() {
      assertEquals(-30, calculator.addNumbers(-15, -15));
    }

    @Test
    public void testAddNumbersMethod_whenNumbersAreZero() {
      assertEquals(0, calculator.addNumbers(0, 0));
    }
  }
}

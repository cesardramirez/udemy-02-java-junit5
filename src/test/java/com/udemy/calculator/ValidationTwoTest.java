package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * La anotación @InjectMocks crea una instancia e inyecta todos los mocks creados con la anotación @Mock.
 * La anotación @Mock crea el mock de una clase y que se usa en la instancia inicial de @InjectMocks.
 */
class ValidationTwoTest {

  @InjectMocks
  private Validation validation;

  @Mock
  private ValidNumber validNumber;

  @BeforeEach
  void setUp() {
    // Inicializa e inyecta los mocks definidos.
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddNumbers() {
    // Action
    validation.addNumbers(3, 2);

    // Assert
    verify(validNumber).isValidNumber(3);
  }

  @Test
  void testAddNumbers_whenBothObjectsAreNumbers() {
    // Arrange
    when(validNumber.isValidNumber(3)).thenReturn(true);
    when(validNumber.isValidNumber(2)).thenReturn(true);

    // Action
    int result = validation.addNumbers(3, 2);

    // Assert
    assertEquals(5, result);
    verify(validNumber).isValidNumber(3);
    verify(validNumber).isValidNumber(2);
  }

  @Test
  void testAddNumbers_whenEitherOfTheTwoObjectsIsNotANumber() {
    // Arrange
    when(validNumber.isValidNumber(3)).thenReturn(true);
    when(validNumber.isValidNumber("a")).thenReturn(false);

    // Action
    validation.addNumbers(3, "a");

    // Assert
    verify(validNumber).isValidNumber(3);
    verify(validNumber).isValidNumber("a");
  }

  /**
   * when(mock.method()).thenAnswer(answer) : A diferencia del método thenReturn() que define un valor fijo de retorno
   *   el método thenAnswer() define una operación que se ejecutará en tiempo de ejecución y será la respuesta para el
   *   método mockeado.
   */
  @Test
  void testNumberIntegerSquared() {
    Answer<Integer> answer = invocationOnMock -> 7;
    when(validNumber.doubleToInt(7.7)).thenAnswer(answer);

    assertEquals(14, validation.numberIntegerSquared(7.7));
  }

  /**
   * when(mock.method()).thenAnswer(answer) :
   * Se define un comportamiento puntual en el método validNumber.doubleToInt(...) donde si el argumento enviado
   *   es mayor a 7 se retornará un 5 como respuesta, así, cuando se llame validation.numberIntegerSquared(...)
   *   con ese argumento, retornará un 10 (5+5).
   */
  @Test
  void testNumberIntegerSquaredxx() {
    Answer<Integer> answer = new Answer<Integer>() {
      @Override
      public Integer answer(InvocationOnMock invocation) throws Throwable {
        Double param = invocation.getArgument(0, Double.class);
        if (param < 7) {
          return param.intValue();
        }
        return 5;
      }
    };

    when(validNumber.doubleToInt(7.7)).thenAnswer(answer);

    assertEquals(8, validation.numberIntegerSquared(7.7));
  }
}

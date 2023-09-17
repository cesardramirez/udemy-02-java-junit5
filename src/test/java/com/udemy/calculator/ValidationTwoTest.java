package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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

  @Mock
  private Print print;

  @Captor
  private ArgumentCaptor<Integer> captor;

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
  void testNumberIntegerSquared_withAnswerLambda() {
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
  void testNumberIntegerSquared_whenAnswerMethod() {
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

  @Test
  void testAddNumbers_withPatternAAA() {
    // Arrange
    when(validNumber.isValidNumber(4)).thenReturn(true);
    when(validNumber.isValidNumber(5)).thenReturn(true);

    // Action
    int result = validation.addNumbers(4, 5);

    // Assert
    assertEquals(9, result);
  }

  @Test
  void testAddNumbers_withPatternGWT() {
    // Given
    given(validNumber.isValidNumber(4)).willReturn(true);
    given(validNumber.isValidNumber(5)).willReturn(true);

    // When
    int result = validation.addNumbers(4, 5);

    // Then
    assertEquals(9, result);
  }

  /**
   * En los métodos when() de mockito, en los parámetros del método a mockear enviamos el valor exacto del argumento
   *   para que tengamos una respuesta puntual en thenReturn().
   * Pero, existe la opción de que realmente no nos interesa definir un valor específico
   *   y que puede ser "cualquiera" o "any".
   * De esta forma, la definición inicial que es:
   *   when(mock.method(param)).thenReturn(value)
   * cambiaría por:
   *   when(mock.method(any())).thenReturn(value)
   * así, se define que cualquier valor que se envíe como parámetro esperamos el resultado definido en el value
   *   para ese método mockeado.
   * <p>
   * Los any() pueden ser genéricos o se puede definir su tipo específico anyInt(), anyString(), etc.
   */
  @Test
  void testAddNumbers_withArgumentMatcher() {
    // Arrange
    when(validNumber.isValidNumber(anyInt())).thenReturn(true);

    // Action
    int result = validation.addNumbers(4, 5);

    // Assert
    assertEquals(9, result);
  }

  /**
   * El método verify() también permite verificar métodos que no tienen un valor por retorno (void) y entre otras cosas, como:
   *   times() : Define el número deseado de invocaciones esperadas.
   *   never() : Define que nunca debe ingresar por ese caso.
   *   atLeast() : Define que al menos debe ingresar un número mínimo de invocaciones.
   *   atMost() : Define el como mucho debe ingresar un número máximo de invocaciones.
   */
  @Test
  void testAddPrint_withVerifyOptionsMethod() {
    // Arrange
    when(validNumber.isValidNumber(4)).thenReturn(true);

    // Action
    validation.addPrint(4, 4);

    // Assert
    verify(validNumber, times(2)).isValidNumber(4);
    verify(validNumber, never()).isValidNumber(99);
    verify(validNumber, atLeast(1)).isValidNumber(4);
    verify(validNumber, atMost(3)).isValidNumber(4);
    verify(print).showMessage(anyInt());
    verify(print).showMessage(8);
    verify(print, never()).showError();
  }

  /**
   * Por medio de Mockito Captor y ArgumentCaptor existe la posibilidad de "capturar" el argumento de un método que está interno
   *   al método a probar y luego validar su valor.
   *   Se define el tipo del Captor <Integer> y luego "obtener" el número para luego validar si es el que se envió como argumento.
   */
  @Test
  void testAddPrint_withCaptorArgumentValue() {
    // Arrange
    when(validNumber.isValidNumber(4)).thenReturn(true);
    when(validNumber.isValidNumber(5)).thenReturn(true);

    // Action
    validation.addPrint(4, 5);

    // Assert
    //verify(print).showMessage(9);
    verify(print).showMessage(captor.capture());
    assertEquals(9, captor.getValue().intValue());
  }
}

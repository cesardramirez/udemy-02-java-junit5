package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ValidationOneTest {

  private Validation validation;
  private ValidNumber validNumber;

  @BeforeEach
  void setUp() {
    validNumber = Mockito.mock(ValidNumber.class);
    validation = new Validation(validNumber);
  }

  /**
   * El método verify() verifica que haya ingresado al método interno correctamente.
   * Debe coincidir los parámetros que se envían para que el verify sea exitoso.
   * verify(mockObject).method(value);
   * <p>
   * El primer verify() con valor 3, a pesar de que el test sea exitoso en ningun momento ejecuta el
   *   validNumber.isValidNumber(3), ya que esto será un false internamente.
   * El segundo verify() con valor 2 indica que nunca ha sido invocado ya que el mock tiene un comportamiento diferente,
   *   por no ser un objeto real. Este devolverá unos valores estandar y para que funcione,
   *   es necesario utilizar el when() o doReturn().
   */
  @Test
  void testAddNumbers() {
    // Action
    validation.addNumbers(3, 2);

    // Assert
    verify(validNumber).isValidNumber(3);
    //verify(validNumber).isValidNumber(2);
  }

  /**
   * when(mock.method()).thenReturn(value) : Se define un comportamiento específico para el mock.
   *   Cuando llame al método específico retorne un valor en específico.
   */
  @Test
  void testAddNumbers_withTheWhenMethod() {
    // Arrange
    when(validNumber.isValidNumber(3)).thenReturn(false);

    // Action
    boolean result = validNumber.isValidNumber(3);

    // Assert
    verify(validNumber).isValidNumber(3);
    assertFalse(result);
  }
}
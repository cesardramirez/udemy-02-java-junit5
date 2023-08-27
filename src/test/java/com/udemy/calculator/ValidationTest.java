package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class ValidationTest {

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

    // Arrange, Action,
    validation.addNumbers(3, 2);

    // Assert
    verify(validNumber).isValidNumber(3);
    //verify(validNumber).isValidNumber(2);
  }
}
package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    validation.addNumbers(3, 2);

    // Assert
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
}

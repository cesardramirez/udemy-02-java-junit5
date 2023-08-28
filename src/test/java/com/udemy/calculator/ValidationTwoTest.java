package com.udemy.calculator;

import com.udemy.util.ValidNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

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

    // Arrange, Action,
    validation.addNumbers(3, 2);

    // Assert
    verify(validNumber).isValidNumber(3);
  }
}

package com.udemy.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WebServiceTest {

  private WebService webService;

  @Mock
  private Callback callback;

  @BeforeEach
  void setUp() {
    webService = new WebService();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testValidateLogin_withSuccessResponse() {
    assertTrue(webService.validateLogin("Cesar", "1234"));
  }

  @Test
  void testValidateLogin_withWrongResponse() {
    assertFalse(webService.validateLogin("Maria", "AAAA"));
  }

  @Test
  void testLogin_withSuccessResponse() {
    webService.login("Cesar", "1234", callback);

    verify(callback).onSuccess("Acceso correcto.");
  }

  @Test
  void testLogin_withWrongResponse() {
    webService.login("Maria", "AAAA", callback);

    verify(callback).onFail("Error al acceder.");
  }
}

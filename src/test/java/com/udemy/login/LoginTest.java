package com.udemy.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doAnswer;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class LoginTest {

  @InjectMocks
  private Login login;

  @Mock
  private WebService webService;

  @Captor
  private ArgumentCaptor<Callback> callbackArgumentCaptor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDoLogin_withSuccessResponse() {
    // Arrange
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        String user = (String) invocationOnMock.getArguments()[0];
        String password = (String) invocationOnMock.getArguments()[1];
        Callback callback = (Callback) invocationOnMock.getArguments()[2];

        assertEquals("Cesar", user);
        assertEquals("12345", password);

        // Define que va a llamar el caso del callback cuando es exitoso.
        callback.onSuccess("OK Test");

        // Por ser un método void, retorna por defecto un null.
        return null;
      }
    }).when(webService).login(anyString(), anyString(), any(Callback.class));

    // Action
    login.doLogin();

    // Assert
    verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
    assertTrue(login.isLogin);
  }

  @Test
  void testDoLogin_withWrongResponse() {
    // Arrange
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        String user = (String) invocationOnMock.getArguments()[0];
        String password = (String) invocationOnMock.getArguments()[1];
        Callback callback = (Callback) invocationOnMock.getArguments()[2];

        assertEquals("Cesar", user);
        assertEquals("12345", password);

        // Define que va a llamar el caso del callback cuando falla.
        callback.onFail("Fail Test");

        // Por ser un método void, retorna por defecto un null.
        return null;
      }
    }).when(webService).login(anyString(), anyString(), any(Callback.class));

    // Action
    login.doLogin();

    // Assert
    verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
    assertFalse(login.isLogin);
  }

  /**
   * Se "captura" el argumento Callback para luego "obtener" el valor para llamar al método exitoso.
   */
  @Test
  void testDoLogin_withArgumentCaptor_shouldIsLoginTrue() {
    // Action
    login.doLogin();

    // Assert
    verify(webService, times(1)).login(anyString(), anyString(), callbackArgumentCaptor.capture());
    assertFalse(login.isLogin);

    Callback callback = callbackArgumentCaptor.getValue();
    callback.onSuccess("Ok Test");

    assertTrue(login.isLogin);
  }

  /**
   * Se "captura" el argumento Callback para luego "obtener" el valor para llamar al método fallido.
   */
  @Test
  void testDoLogin_withArgumentCaptor_shouldIsLoginFalse() {
    // Action
    login.doLogin();

    // Assert
    verify(webService, times(1)).login(anyString(), anyString(), callbackArgumentCaptor.capture());
    assertFalse(login.isLogin);

    Callback callback = callbackArgumentCaptor.getValue();
    callback.onFail("Fail Test");

    assertFalse(login.isLogin);
  }
}
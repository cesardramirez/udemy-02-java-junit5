package com.udemy.login;

public class WebService {

  void login(String user, String password, Callback callback) {
    if (validateLogin(user, password)) {
      callback.onSuccess("Acceso correcto.");
    } else {
      callback.onFail("Error al acceder.");
    }
  }

  boolean validateLogin(String user, String password) {
    return user.equals("Cesar") && password.equals("1234");
  }
}

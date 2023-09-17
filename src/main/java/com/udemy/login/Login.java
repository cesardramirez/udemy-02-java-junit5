package com.udemy.login;

public class Login {

  private WebService webService;
  public boolean isLogin;

  public Login(WebService webService) {
    this.webService = webService;
    this.isLogin = false;
  }

  void doLogin() {
    webService.login("Cesar", "12345", new Callback() {
      @Override
      public void onSuccess(String response) {
        System.out.println(response);
        isLogin = true;
      }

      @Override
      public void onFail(String error) {
        System.out.println(error);
        isLogin = false;
      }
    });
  }
}

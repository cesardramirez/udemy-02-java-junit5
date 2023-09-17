package com.udemy.login;

public interface Callback {

  void onSuccess(String response);
  void onFail(String error);
}

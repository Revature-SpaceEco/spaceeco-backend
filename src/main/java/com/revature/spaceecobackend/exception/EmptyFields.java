package com.revature.spaceecobackend.exception;

public class EmptyFields extends Exception {
  public EmptyFields() {
  }

  public EmptyFields(String message) {
    super(message);
  }

  public EmptyFields(String message, Throwable cause) {
    super(message, cause);
  }

  public EmptyFields(Throwable cause) {
    super(cause);
  }

  public EmptyFields(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
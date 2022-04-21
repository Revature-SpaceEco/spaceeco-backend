package com.revature.spaceecobackend.exception;

public class BillingDetailsNotFound extends Exception {
    public BillingDetailsNotFound() {
    }

    public BillingDetailsNotFound(String message) {
        super(message);
    }

    public BillingDetailsNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public BillingDetailsNotFound(Throwable cause) {
        super(cause);
    }

    public BillingDetailsNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

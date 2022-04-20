package com.revature.spaceecobackend.exception;

public class OrderNotFound extends Exception{
    public OrderNotFound() {
    }

    public OrderNotFound(String message) {
        super(message);
    }

    public OrderNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFound(Throwable cause) {
        super(cause);
    }

    public OrderNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

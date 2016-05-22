package com.mct.appdirect.error;

public class TransportException extends RuntimeException {
    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}

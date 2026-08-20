package org.lito.jakarta.exception;

public class MedioNotFoundException extends RuntimeException {
    public MedioNotFoundException(String message) {
        super(message);
    }
}
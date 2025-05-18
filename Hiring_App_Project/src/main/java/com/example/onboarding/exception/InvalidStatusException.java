package com.example.onboarding.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status) {
        super("Invalid status: " + status);
    }
}

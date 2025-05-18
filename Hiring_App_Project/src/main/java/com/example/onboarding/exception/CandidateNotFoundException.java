package com.example.onboarding.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(Long id) {
        super("Candidate with ID " + id + " not found.");
    }
}

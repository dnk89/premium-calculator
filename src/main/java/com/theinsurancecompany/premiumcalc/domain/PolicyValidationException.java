package com.theinsurancecompany.premiumcalc.domain;

public class PolicyValidationException extends RuntimeException {
    public PolicyValidationException(String message) {
        super(message);
    }
}

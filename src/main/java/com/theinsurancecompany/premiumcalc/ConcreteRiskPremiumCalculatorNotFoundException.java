package com.theinsurancecompany.premiumcalc;

public class ConcreteRiskPremiumCalculatorNotFoundException extends RuntimeException {
    public ConcreteRiskPremiumCalculatorNotFoundException(RiskType riskType) {
        super(getExceptionMessage(riskType));
    }

    private static String getExceptionMessage(RiskType riskType) {
        return String.format("Premium calculator not found for risk type: %s", riskType);
    }
}

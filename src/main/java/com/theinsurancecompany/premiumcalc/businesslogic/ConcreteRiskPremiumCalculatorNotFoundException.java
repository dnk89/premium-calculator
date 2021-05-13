package com.theinsurancecompany.premiumcalc.businesslogic;

import com.theinsurancecompany.premiumcalc.domain.RiskType;

public class ConcreteRiskPremiumCalculatorNotFoundException extends RuntimeException {
    public ConcreteRiskPremiumCalculatorNotFoundException(RiskType riskType) {
        super(getExceptionMessage(riskType));
    }

    private static String getExceptionMessage(RiskType riskType) {
        return String.format("Premium calculator not found for risk type: %s", riskType);
    }
}

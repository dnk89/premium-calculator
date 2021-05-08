package com.theinsurancecompany.premiumcalc;

public interface ConcreteRiskPremiumCalculatorFactory {
    PremiumCalculator getConcreteRiskPremiumCalculatorFor(RiskType riskType);
}

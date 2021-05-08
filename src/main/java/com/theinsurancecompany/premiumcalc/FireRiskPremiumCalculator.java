package com.theinsurancecompany.premiumcalc;

import java.math.BigDecimal;

public class FireRiskPremiumCalculator extends ConcreteRiskPremiumCalculatorBase {

    public FireRiskPremiumCalculator() {
        super(RiskType.FIRE);
    }

    @Override
    protected BigDecimal calculateCoefficient(BigDecimal totalSumInsured) {
        return totalSumInsured.compareTo(new BigDecimal("100")) > 0 ?
                new BigDecimal("0.024") :
                new BigDecimal("0.014");
    }
}

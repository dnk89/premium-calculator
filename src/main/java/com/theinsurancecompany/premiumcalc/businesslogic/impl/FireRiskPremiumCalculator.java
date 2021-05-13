package com.theinsurancecompany.premiumcalc.businesslogic.impl;

import com.theinsurancecompany.premiumcalc.domain.RiskType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class FireRiskPremiumCalculator extends ConcreteRiskPremiumCalculatorBase {

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

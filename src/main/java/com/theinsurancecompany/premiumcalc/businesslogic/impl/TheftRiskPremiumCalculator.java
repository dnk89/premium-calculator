package com.theinsurancecompany.premiumcalc.businesslogic.impl;

import com.theinsurancecompany.premiumcalc.domain.RiskType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TheftRiskPremiumCalculator extends ConcreteRiskPremiumCalculatorBase {

    public TheftRiskPremiumCalculator() {
        super(RiskType.THEFT);
    }

    @Override
    protected BigDecimal calculateCoefficient(BigDecimal totalSumInsured) {
        return totalSumInsured.compareTo(new BigDecimal("15")) >= 0 ?
                new BigDecimal("0.05") :
                new BigDecimal("0.11");
    }
}

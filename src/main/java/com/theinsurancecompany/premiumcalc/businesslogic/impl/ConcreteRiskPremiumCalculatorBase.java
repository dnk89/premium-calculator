package com.theinsurancecompany.premiumcalc.businesslogic.impl;

import com.theinsurancecompany.premiumcalc.domain.Policy;
import com.theinsurancecompany.premiumcalc.domain.PolicyObjectItem;
import com.theinsurancecompany.premiumcalc.businesslogic.PremiumCalculator;
import com.theinsurancecompany.premiumcalc.domain.RiskType;

import java.math.BigDecimal;

abstract class ConcreteRiskPremiumCalculatorBase implements PremiumCalculator {

    private final RiskType riskType;

    protected ConcreteRiskPremiumCalculatorBase(RiskType riskType) {
        this.riskType = riskType;
    }

    @Override
    public BigDecimal calculate(Policy policy) {
        BigDecimal totalSumInsured = getTotalSumInsured(policy);
        BigDecimal coefficient = calculateCoefficient(totalSumInsured);
        return totalSumInsured.multiply(coefficient);
    }

    private BigDecimal getTotalSumInsured(Policy policy) {
        return policy.getObjects().stream()
                .map(o -> o.getItems().stream()
                        .filter(i -> i.getRiskType() == riskType)
                        .map(PolicyObjectItem::getSumInsured)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected abstract BigDecimal calculateCoefficient(BigDecimal totalSumInsured);
}

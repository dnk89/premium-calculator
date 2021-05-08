package com.theinsurancecompany.premiumcalc;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

@Component
public class TotalPremiumCalculator implements PremiumCalculator {

    private ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory;

    public TotalPremiumCalculator(ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory) {
        this.concreteRiskPremiumCalculatorFactory = concreteRiskPremiumCalculatorFactory;
    }

    @Override
    public BigDecimal calculate(Policy policy) {
        return calculateForAllRiskTypes(policy);
    }

    private BigDecimal calculateForAllRiskTypes(Policy policy) {
        return Stream.of(RiskType.values())
                .map(riskType -> calculateForRiskType(riskType, policy))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateForRiskType(RiskType riskType, Policy policy) {
        return concreteRiskPremiumCalculatorFactory.getConcreteRiskPremiumCalculatorFor(riskType).calculate(policy);
    }
}

package com.theinsurancecompany.premiumcalc;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultConcreteRiskPremiumCalculatorFactory implements ConcreteRiskPremiumCalculatorFactory {
    private final Map<RiskType, PremiumCalculator> calculators = new HashMap<>();

    public DefaultConcreteRiskPremiumCalculatorFactory() {
        initializeCalculators();
    }

    private void initializeCalculators() {
        calculators.put(RiskType.FIRE, new FireRiskPremiumCalculator());
        calculators.put(RiskType.THEFT, new TheftRiskPremiumCalculator());
    }

    @Override
    public PremiumCalculator getConcreteRiskPremiumCalculatorFor(RiskType riskType) {
        PremiumCalculator calculator = calculators.get(riskType);
        if (calculator == null)
            throw new ConcreteRiskPremiumCalculatorNotFoundException(riskType);
        return calculator;
    }
}

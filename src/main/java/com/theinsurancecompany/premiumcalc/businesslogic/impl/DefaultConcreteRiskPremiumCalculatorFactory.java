package com.theinsurancecompany.premiumcalc.businesslogic.impl;

import com.theinsurancecompany.premiumcalc.businesslogic.ConcreteRiskPremiumCalculatorNotFoundException;
import com.theinsurancecompany.premiumcalc.businesslogic.PremiumCalculator;
import com.theinsurancecompany.premiumcalc.domain.RiskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class DefaultConcreteRiskPremiumCalculatorFactory implements ConcreteRiskPremiumCalculatorFactory {
    private final Map<RiskType, PremiumCalculator> calculators = new HashMap<>();
    private boolean calculatorsInitialized = false;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PremiumCalculator getConcreteRiskPremiumCalculatorFor(RiskType riskType) {
        initializeCalculators();
        PremiumCalculator calculator = calculators.get(riskType);
        if (calculator == null)
            throw new ConcreteRiskPremiumCalculatorNotFoundException(riskType);
        return calculator;
    }

    private void initializeCalculators() {
        if (calculatorsInitialized)
            return;

        calculators.put(RiskType.FIRE, applicationContext.getBean(FireRiskPremiumCalculator.class));
        calculators.put(RiskType.THEFT, applicationContext.getBean(TheftRiskPremiumCalculator.class));
        calculatorsInitialized = true;
    }
}

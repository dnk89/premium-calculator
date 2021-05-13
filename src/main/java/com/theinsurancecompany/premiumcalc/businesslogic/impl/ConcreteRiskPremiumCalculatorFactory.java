package com.theinsurancecompany.premiumcalc.businesslogic.impl;

import com.theinsurancecompany.premiumcalc.domain.RiskType;
import com.theinsurancecompany.premiumcalc.businesslogic.PremiumCalculator;

interface ConcreteRiskPremiumCalculatorFactory {
    PremiumCalculator getConcreteRiskPremiumCalculatorFor(RiskType riskType);
}

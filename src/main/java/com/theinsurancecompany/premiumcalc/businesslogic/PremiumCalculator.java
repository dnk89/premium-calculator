package com.theinsurancecompany.premiumcalc.businesslogic;

import com.theinsurancecompany.premiumcalc.domain.Policy;

import java.math.BigDecimal;

public interface PremiumCalculator {
    BigDecimal calculate(Policy policy);
}

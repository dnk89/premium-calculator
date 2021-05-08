package com.theinsurancecompany.premiumcalc;

import java.math.BigDecimal;

public interface PremiumCalculator {
    BigDecimal calculate(Policy policy);
}

public interface ConcreteRiskPremiumCalculatorFactory {
    PremiumCalculator getConcreteRiskPremiumCalculatorFor(RiskType riskType);
}

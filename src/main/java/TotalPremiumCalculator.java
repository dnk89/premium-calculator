import java.math.BigDecimal;
import java.math.RoundingMode;

public class TotalPremiumCalculator implements PremiumCalculator {

    private ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory;

    public TotalPremiumCalculator(ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory) {
        this.concreteRiskPremiumCalculatorFactory = concreteRiskPremiumCalculatorFactory;
    }

    @Override
    public BigDecimal calculate(Policy policy) {
        BigDecimal totalPremium = BigDecimal.ZERO;
        for (RiskType riskType : RiskType.values()) {
            PremiumCalculator concreteRiskPremiumCalculator = concreteRiskPremiumCalculatorFactory.getConcreteRiskPremiumCalculatorFor(riskType);
            BigDecimal concreteRiskPremium = concreteRiskPremiumCalculator.calculate(policy);
            totalPremium = totalPremium.add(concreteRiskPremium);
        }
        return totalPremium.setScale(2, RoundingMode.HALF_UP);
    }
}

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class TotalPremiumCalculator implements PremiumCalculator {

    private ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory;

    public TotalPremiumCalculator(ConcreteRiskPremiumCalculatorFactory concreteRiskPremiumCalculatorFactory) {
        this.concreteRiskPremiumCalculatorFactory = concreteRiskPremiumCalculatorFactory;
    }

    @Override
    public BigDecimal calculate(Policy policy) {
        return Stream.of(RiskType.values())
                .map(t -> calculateForRiskType(t, policy))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateForRiskType(RiskType riskType, Policy policy) {
        return concreteRiskPremiumCalculatorFactory.getConcreteRiskPremiumCalculatorFor(riskType).calculate(policy);
    }
}

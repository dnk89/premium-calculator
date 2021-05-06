import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultPremiumCalculator implements PremiumCalculator {
    @Override
    public BigDecimal calculate(Policy policy) {
        BigDecimal sumInsuredFire = policy.getObjects().stream()
                .map(o -> o.getItems().stream()
                        .filter(i -> i.getRiskType() == RiskType.FIRE)
                        .map(PolicyObjectItem::getSumInsured)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal sumInsuredTheft = policy.getObjects().stream()
                .map(o -> o.getItems().stream()
                        .filter(i -> i.getRiskType() == RiskType.THEFT)
                        .map(PolicyObjectItem::getSumInsured)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal premiumFireCoefficient = sumInsuredFire.compareTo(new BigDecimal("100")) > 0 ?
                new BigDecimal("0.024") : new BigDecimal("0.014");
        BigDecimal premiumFire = sumInsuredFire.multiply(premiumFireCoefficient);

        BigDecimal premiumTheftCoefficient = sumInsuredTheft.compareTo(new BigDecimal("15")) >= 0 ?
                new BigDecimal("0.05") : new BigDecimal("0.11");
        BigDecimal premiumTheft = sumInsuredTheft.multiply(premiumTheftCoefficient);

        return premiumFire.add(premiumTheft).setScale(2, RoundingMode.HALF_UP);
    }
}

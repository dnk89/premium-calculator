import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PremiumCalculatorTests {

    private Policy.Builder policyBuilder;
    private PolicyObject.Builder objectBuilder;

    @BeforeEach
    public void init() {
        policyBuilder = new Policy.Builder();
        objectBuilder = new PolicyObject.Builder();
    }

    @Test
    public void policy_with_one_object_and_two_sub_objects_criteria() {
        PremiumCalculator calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        PolicyObject house = objectBuilder
                .withName("House")
                .withItem(new PolicyObjectItem("TV", new BigDecimal("100.00"), RiskType.FIRE))
                .withItem(new PolicyObjectItem("Refrigerator", new BigDecimal("8.00"), RiskType.THEFT))
                .build();
        Policy policy = policyBuilder
                .withNumber("LV20-02-100000-5")
                .withStatus(PolicyStatus.REGISTERED)
                .withObject(house)
                .build();

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertThat(calculatedPremium, is(equalTo(new BigDecimal("2.28"))));
    }

    @Test
    public void policy_with_total_sums_criteria() {
        PremiumCalculator calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        PolicyObject house = objectBuilder
                .withName("House")
                .withItem(new PolicyObjectItem("TV", new BigDecimal("104.45"), RiskType.FIRE))
                .withItem(new PolicyObjectItem("Blender", new BigDecimal("8.06"), RiskType.THEFT))
                .build();
        PolicyObject garage = objectBuilder
                .reset()
                .withName("Garage")
                .withItem(new PolicyObjectItem("Bicycle", new BigDecimal("395.55"), RiskType.FIRE))
                .withItem(new PolicyObjectItem("Chainsaw", new BigDecimal("94.45"), RiskType.THEFT))
                .build();
        Policy policy = policyBuilder
                .withNumber("LV20-02-100000-5")
                .withStatus(PolicyStatus.REGISTERED)
                .withObject(house)
                .withObject(garage)
                .build();

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertThat(calculatedPremium, is(equalTo(new BigDecimal("17.13"))));
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PremiumCalculatorTests {

    private Policy.Builder policyBuilder;

    @BeforeEach
    public void init() {
        policyBuilder = new Policy.Builder();
    }

    @Test
    public void policy_with_one_object_and_two_sub_objects_criteria() {
        PremiumCalculator calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        PolicyObject policyObject = new PolicyObject("House");
        policyObject.addItem(new PolicyObjectItem("TV", new BigDecimal("100.00"), RiskType.FIRE));
        policyObject.addItem(new PolicyObjectItem("Refrigerator", new BigDecimal("8.00"), RiskType.THEFT));
        Policy policy = policyBuilder
                .withNumber("LV20-02-100000-5")
                .withStatus(PolicyStatus.REGISTERED)
                .withObject(policyObject)
                .build();

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertThat(calculatedPremium, is(equalTo(new BigDecimal("2.28"))));
    }

    @Test
    public void policy_with_total_sums_criteria() {
        PremiumCalculator calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        PolicyObject house = new PolicyObject("House");
        house.addItem(new PolicyObjectItem("TV", new BigDecimal("104.45"), RiskType.FIRE));
        house.addItem(new PolicyObjectItem("Blender", new BigDecimal("8.06"), RiskType.THEFT));
        PolicyObject garage = new PolicyObject("Garage");
        garage.addItem(new PolicyObjectItem("Bicycle", new BigDecimal("395.55"), RiskType.FIRE));
        garage.addItem(new PolicyObjectItem("Chainsaw", new BigDecimal("94.45"), RiskType.THEFT));
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

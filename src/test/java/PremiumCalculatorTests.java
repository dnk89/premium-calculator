import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PremiumCalculatorTests {

    private Policy.Builder policyBuilder;
    private PolicyObject.Builder objectBuilder;
    private PolicyObjectItem.Builder itemBuilder;

    @BeforeEach
    public void init() {
        policyBuilder = new Policy.Builder();
        objectBuilder = new PolicyObject.Builder();
        itemBuilder = new PolicyObjectItem.Builder();
    }

    @Test
    public void policy_with_one_object_and_two_sub_objects_criteria() {
        PremiumCalculator calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        PolicyObjectItem tv = itemBuilder
                .withName("TV")
                .withSumInsured(new BigDecimal("100.00"))
                .withRiskType(RiskType.FIRE)
                .build();
        PolicyObjectItem refrigerator = itemBuilder
                .reset()
                .withName("Refrigerator")
                .withSumInsured(new BigDecimal("8.00"))
                .withRiskType(RiskType.THEFT)
                .build();
        PolicyObject house = objectBuilder
                .withName("House")
                .withItem(tv)
                .withItem(refrigerator)
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
        PolicyObjectItem tv = itemBuilder
                .withName("TV")
                .withSumInsured(new BigDecimal("104.45"))
                .withRiskType(RiskType.FIRE)
                .build();
        PolicyObjectItem blender = itemBuilder
                .reset()
                .withName("Blender")
                .withSumInsured(new BigDecimal("8.06"))
                .withRiskType(RiskType.THEFT)
                .build();
        PolicyObject house = objectBuilder
                .withName("House")
                .withItem(tv)
                .withItem(blender)
                .build();
        PolicyObjectItem bicycle = itemBuilder
                .reset()
                .withName("Bicycle")
                .withSumInsured(new BigDecimal("395.55"))
                .withRiskType(RiskType.FIRE)
                .build();
        PolicyObjectItem chainsaw = itemBuilder
                .reset()
                .withName("Chainsaw")
                .withSumInsured(new BigDecimal("94.45"))
                .withRiskType(RiskType.THEFT)
                .build();
        PolicyObject garage = objectBuilder
                .reset()
                .withName("Garage")
                .withItem(bicycle)
                .withItem(chainsaw)
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

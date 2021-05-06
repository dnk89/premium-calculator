import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremiumCalculatorTests {

    @Test
    public void policy_with_one_object_and_two_sub_objects_criteria() {
        PremiumCalculator calculator = new DefaultPremiumCalculator();
        PolicyObject policyObject = new PolicyObject("House");
        policyObject.addItem(new PolicyObjectItem("TV", new BigDecimal("100.00"), RiskType.FIRE));
        policyObject.addItem(new PolicyObjectItem("Refrigerator", new BigDecimal("8.00"), RiskType.THEFT));
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        policy.addObject(policyObject);

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertEquals(0, calculatedPremium.compareTo(new BigDecimal("2.28")));
    }

    @Test
    public void policy_with_total_sums_criteria() {
        PremiumCalculator calculator = new DefaultPremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED);
        PolicyObject policyObject = new PolicyObject("House");
        policyObject.addItem(new PolicyObjectItem("TV", new BigDecimal("104.45"), RiskType.FIRE));
        policyObject.addItem(new PolicyObjectItem("Blender", new BigDecimal("8.06"), RiskType.THEFT));
        policy.addObject(policyObject);
        policyObject = new PolicyObject("Garage");
        policyObject.addItem(new PolicyObjectItem("Bicycle", new BigDecimal("395.55"), RiskType.FIRE));
        policyObject.addItem(new PolicyObjectItem("Chainsaw", new BigDecimal("94.45"), RiskType.THEFT));
        policy.addObject(policyObject);

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertEquals(0, calculatedPremium.compareTo(new BigDecimal("17.13")));
    }
}

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
}

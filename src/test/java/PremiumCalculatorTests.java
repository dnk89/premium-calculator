import com.theinsurancecompany.premiumcalc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PremiumCalculatorTests {

    private PremiumCalculator calculator;
    private Policy.Builder policyBuilder;
    private PolicyObject.Builder objectBuilder;

    @BeforeEach
    public void init() {
        calculator = new TotalPremiumCalculator(new DefaultConcreteRiskPremiumCalculatorFactory());
        policyBuilder = new Policy.Builder();
        objectBuilder = new PolicyObject.Builder();
    }

    @Test
    public void policy_with_one_object_and_two_sub_objects_criteria() {
        Policy policy = policyBuilder
                .withNumber("LV20-02-100000-5")
                .withStatus(PolicyStatus.REGISTERED)
                .withObject(objectBuilder
                        .withName("House")
                        .withFireRiskItem("TV", new BigDecimal("100.00"))
                        .withTheftRiskItem("Refrigerator", new BigDecimal("8.00"))
                        .build())
                .build();

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertThat(calculatedPremium, is(equalTo(new BigDecimal("2.28"))));
    }

    @Test
    public void policy_with_total_sums_criteria() {
        Policy policy = policyBuilder
                .withNumber("LV20-02-100000-5")
                .withStatus(PolicyStatus.REGISTERED)
                .withObject(objectBuilder
                        .withName("House")
                        .withFireRiskItem("TV", new BigDecimal("104.45"))
                        .withTheftRiskItem("Blender", new BigDecimal("8.06"))
                        .build())
                .withObject(objectBuilder.reset()
                        .withName("Garage")
                        .withFireRiskItem("Bicycle", new BigDecimal("395.55"))
                        .withTheftRiskItem("Chainsaw", new BigDecimal("94.45"))
                        .build())
                .build();

        BigDecimal calculatedPremium = calculator.calculate(policy);

        assertThat(calculatedPremium, is(equalTo(new BigDecimal("17.13"))));
    }
}

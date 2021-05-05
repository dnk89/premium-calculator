import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PremiumCalculatorTests {
    @Test
    public void dummyTest() {
        PremiumCalculator calculator = new DefaultPremiumCalculator();

        BigDecimal result = calculator.calculate(new Policy());

        assertNull(result);
    }
}

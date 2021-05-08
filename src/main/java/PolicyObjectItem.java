import java.math.BigDecimal;

public class PolicyObjectItem {

    private final String name;
    private final BigDecimal sumInsured;
    private final RiskType riskType;

    private PolicyObjectItem(Builder builder) {
        this.name = builder.name;
        this.sumInsured = builder.sumInsured;
        this.riskType = builder.riskType;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public static class Builder {
        private static final String EMPTY_NAME_MESSAGE = "Policy object item's name can't be empty.";
        private static final String SUM_INSURED_NOT_SET_MESSAGE = "Policy object item's sum insured should be set.";
        private static final String RISK_TYPE_NOT_SET_MESSAGE = "Policy object item's risk type should be set.";

        private String name;
        private BigDecimal sumInsured;
        private RiskType riskType;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSumInsured(BigDecimal sumInsured) {
            this.sumInsured = sumInsured;
            return this;
        }

        public Builder withRiskType(RiskType riskType) {
            this.riskType = riskType;
            return this;
        }

        public Builder reset() {
            name = null;
            sumInsured = null;
            riskType = null;
            return this;
        }

        public PolicyObjectItem build() {
            PolicyObjectItem item = new PolicyObjectItem(this);
            validate(item);
            return item;
        }

        private void validate(PolicyObjectItem item) {
            if ((item.getName() == null) || item.getName().trim().isEmpty())
                throw new PolicyValidationException(EMPTY_NAME_MESSAGE);
            if (item.getSumInsured() == null)
                throw new PolicyValidationException(SUM_INSURED_NOT_SET_MESSAGE);
            if (item.getRiskType() == null)
                throw new PolicyValidationException(RISK_TYPE_NOT_SET_MESSAGE);
        }
    }
}

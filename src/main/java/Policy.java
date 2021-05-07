import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Policy {

    private final String number;
    private final PolicyStatus status;
    private final List<PolicyObject> objects;

    private Policy(Builder builder) {
        this.number = builder.number;
        this.status = builder.status;
        this.objects = Collections.unmodifiableList(builder.objects);
    }

    public String getNumber() {
        return number;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public List<PolicyObject> getObjects() {
        return objects;
    }

    public static class Builder {
        private static final String EMPTY_NUMBER_MESSAGE = "Policy number can't be empty.";
        private static final String EMPTY_OBJECTS_MESSAGE = "Policy should contain at least one object.";
        private static final String STATUS_NOT_SET_MESSAGE = "Policy status should be set";

        private String number;
        private PolicyStatus status;
        private List<PolicyObject> objects = new ArrayList<>();

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withStatus(PolicyStatus status) {
            this.status = status;
            return this;
        }

        public Builder withObject(PolicyObject object) {
            objects.add(object);
            return this;
        }

        public Builder reset() {
            number = null;
            status = null;
            objects = new ArrayList<>();
            return this;
        }

        public Policy build() {
            Policy policy = new Policy(this);
            validate(policy);
            return policy;
        }

        private void validate(Policy policy) {
            if ((policy.getNumber() == null) || policy.getNumber().trim().isEmpty())
                throw new PolicyValidationException(EMPTY_NUMBER_MESSAGE);
            if (policy.getStatus() == null)
                throw new PolicyValidationException(STATUS_NOT_SET_MESSAGE);
            if (policy.getObjects().size() == 0)
                throw new PolicyValidationException(EMPTY_OBJECTS_MESSAGE);
        }
    }
}

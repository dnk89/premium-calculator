import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolicyObject {

    private final String name;
    private final List<PolicyObjectItem> items;

    private PolicyObject(Builder builder) {
        this.name = builder.name;
        this.items = Collections.unmodifiableList(builder.items);
    }

    public String getName() {
        return name;
    }

    public List<PolicyObjectItem> getItems() {
        return items;
    }

    public static class Builder {
        private static final String EMPTY_NAME_MESSAGE = "Policy object's name can't be empty.";

        private String name;
        private List<PolicyObjectItem> items = new ArrayList<>();

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withItem(PolicyObjectItem item) {
            items.add(item);
            return this;
        }

        public Builder reset() {
            name = null;
            items = new ArrayList<>();
            return this;
        }

        public PolicyObject build() {
            PolicyObject policyObject = new PolicyObject(this);
            validate(policyObject);
            return policyObject;
        }

        private void validate(PolicyObject policyObject) {
            if ((policyObject.getName() == null) || policyObject.getName().trim().isEmpty())
                throw new PolicyValidationException(EMPTY_NAME_MESSAGE);
        }
    }
}

import java.util.ArrayList;
import java.util.List;

public class Policy {

    private final String number;
    private final PolicyStatus status;
    private final List<PolicyObject> objects = new ArrayList<>();

    public Policy(String number, PolicyStatus status) {
        this.number = number;
        this.status = status;
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

    public void addObject(PolicyObject object) {
        objects.add(object);
    }
}

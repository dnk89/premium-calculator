import java.util.ArrayList;
import java.util.List;

public class PolicyObject {

    private final String name;
    private final List<PolicyObjectItem> items = new ArrayList<>();

    public PolicyObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<PolicyObjectItem> getItems() {
        return items;
    }

    public void addItem(PolicyObjectItem item) {
        items.add(item);
    }
}

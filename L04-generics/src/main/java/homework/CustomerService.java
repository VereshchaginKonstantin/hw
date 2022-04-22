package homework;


import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private TreeMap<Customer, String> customers = new TreeMap<>(
            (x, y) -> {
                if (x.getScores() > y.getScores()) {
                    return 1;
                } else if(x.getScores() < y.getScores()) {
                    return -1;
                } else {
                    return 0;
                }
            });

    public Map.Entry<Customer, String> getSmallest() {
        var item = customers.firstEntry();
        return clone(item);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var item = customers.higherEntry(customer);
        return clone(item);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private Map.Entry<Customer, String> clone(Map.Entry<Customer, String> item) {
        return item != null ?
                Map.entry(new Customer(
                        item.getKey().getId(),
                        item.getKey().getName(),
                        item.getKey().getScores()),
                item.getValue()) :
                null;
    }
}

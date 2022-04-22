package homework;


import java.util.Stack;

public class CustomerReverseOrder {

    private Stack<Customer> store = new Stack<>();

    public void add(Customer customer) {
        store.add(customer);
    }

    public Customer take() {
        return store.pop();
    }
}

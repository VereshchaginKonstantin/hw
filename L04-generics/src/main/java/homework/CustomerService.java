package homework;


import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private TreeMap<Customer, String> customers = new TreeMap<>(
            (x, y) -> {
                if(x.getScores() > y.getScores()) {
                    return 1;
                } else if(x.getScores() < y.getScores()) {
                    return -1;
                } else {
                    return 0;
                }
            });

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return customers.firstEntry(); // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customers.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}

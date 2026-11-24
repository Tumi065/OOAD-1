package BankingSystemOOAD;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps the customers in memory while the program runs.
 * In Task 7 the DAO classes will replace this.
 */
public class BankStore {

    private final List<Customer> customers = new ArrayList<>();
    private int customerCount = 0;
    private int accountCount = 0;

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findByUsername(String username) {
        for (Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                return c;
            }
        }
        return null;
    }

    public String nextCustomerId() {
        customerCount++;
        return String.format("C%03d", customerCount);
    }

    /** prefix is SAV, INV or CHQ, giving numbers like SAV-001 */
    public String nextAccountNumber(String prefix) {
        accountCount++;
        return String.format("%s-%03d", prefix, accountCount);
    }
}

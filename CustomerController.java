package BankingSystemOOAD;

public class CustomerController {

    public void registerCustomer(
            String name,
            String address,
            String phone,
            String username,
            String password) {

        System.out.println("Customer Registration");

        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Username: " + username);

        System.out.println("Customer registered successfully.");
    }
}
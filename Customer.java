package BankingSystemOOAD;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// ENCAPSULATION:
// Customer details are protected using private fields
public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String phone;
    private String username;
    private String passwordHash;
    private List<Account> accounts;
    private String companyName; // only for company customers, otherwise null

    public Customer(String customerId, String name, String address, String phone,
                    String username, String password) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.passwordHash = hash(password);
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account a) {
        if (a == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.add(a);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public boolean verifyPassword(String pw) {
        return passwordHash.equals(hash(pw));
    }

    public String getName() {
        return name;
    }

    public void setFirstname(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // Passwords are never stored in plain text
    private static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
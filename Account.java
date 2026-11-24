package BankingSystemOOAD;

import java.util.ArrayList;
import java.util.List;

// ENCAPSULATION:
// Account data is hidden using private fields. AND,
// ABSTRACTION
public abstract class Account {
    private String accountNumber;
    private double balance;
    private String branch;
    private Customer owner;
    private List<Transaction> transactions;

    public Account(String accountNumber, String branch, Customer owner) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

// METHOD OVERLOADING
    public void deposit(double amount) {
        deposit(amount, "Deposit");
    }

    public void deposit(double amount, String note) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        updateBalance(amount);
        recordTransaction(TransactionType.DEPOSIT, amount, note);
    }

    public abstract String getAccountType();

    // Adds a positive amount or subtracts a negative amount
    protected void updateBalance(double amount) {
        balance += amount;
    }

    // Helper so subclasses (withdraw, interest) can log transactions
    protected void recordTransaction(TransactionType type, double amount, String note) {
        transactions.add(new Transaction(type, amount, balance, note));
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public String getBranch() {
        return branch;
    }

    public Customer getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
package BankingSystemOOAD;

import java.time.LocalDateTime;

public class Transaction {
    private static int nextId = 1;

    private int transactionId;
    private TransactionType type;
    private double amount;
    private LocalDateTime dateTime;
    private double balanceAfter;
    private String note;

    public Transaction(TransactionType type, double amount, double balanceAfter, String note) {
        this.transactionId = nextId++;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.balanceAfter = balanceAfter;
        this.note = note;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s | Amount: %.2f | Balance after: %.2f | %s",
                transactionId, dateTime, type, amount, balanceAfter, note);
    }
}
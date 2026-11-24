package BankingSystemOOAD;

// INHERITANCE
public class ChequeAccount extends Account {
    private String employerName;
    private String employerAddress;

    public ChequeAccount(String accountNumber, String branch, Customer owner,
                         String employerName, String employerAddress) {
        super(accountNumber, branch, owner);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            return false;
        }
        updateBalance(-amount);
        recordTransaction(TransactionType.WITHDRAWAL, amount, "Withdrawal");
        return true;
    }

    @Override
    public String getAccountType() {
        return "Cheque";
    }

    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }
}

package BankingSystemOOAD;

// INHERITANCE
public class SavingsAccount extends Account implements InterestCalculations {
    private static final double INTEREST_RATE = 0.0005;

    public SavingsAccount(String accountNumber, String branch, Customer owner) {
        super(accountNumber, branch, owner);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }

    @Override
    public void payInterest() {
        double interest = calculateInterest();
        updateBalance(interest);
        recordTransaction(TransactionType.INTEREST, interest, "Interest paid");
    }
}

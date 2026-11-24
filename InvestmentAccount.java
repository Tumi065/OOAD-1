package BankingSystemOOAD;

// INHERITANCE
public class InvestmentAccount extends Account implements InterestCalculations {
    private static final double INTEREST_RATE = 0.05;
    private static final double MIN_OPENING_DEPOSIT = 500.00;

    public InvestmentAccount(String accountNumber, String branch, Customer owner, double openingDeposit) {
        super(accountNumber, branch, owner);
        if (openingDeposit < MIN_OPENING_DEPOSIT) {
            throw new IllegalArgumentException(
                    "Investment account needs a minimum opening deposit of " + MIN_OPENING_DEPOSIT);
        }
        deposit(openingDeposit, "Opening deposit");
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            return false;
        }
        updateBalance(-amount);
        recordTransaction(TransactionType.WITHDRAWAL, amount, "Withdrawal");
        return true;
    }
// METHOD OVERRIDING:
    @Override
    public String getAccountType() {
        return "Investment";
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

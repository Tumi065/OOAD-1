package BankingSystemOOAD;
// Interface defines behaviours for accounts that earn interest 
public interface InterestCalculations {
    double getInterestRate();
    double calculateInterest();
    void payInterest();
}
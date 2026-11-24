package BankingSystemOOAD;

public class AccountController {

    public void viewAccount() {

        System.out.println("Displaying account details...");
    }

    public void openAccount() {

        System.out.println("Opening account...");
    }

    public void deposit(double amount) {

        System.out.println("Deposited: P" + amount);
    }

    public void withdraw(double amount) {

        System.out.println("Withdrawn: P" + amount);
    }

    public void payInterest() {

        System.out.println("Interest paid.");
    }
}
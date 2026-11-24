package BankingSystemOOAD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Customer> customers = new ArrayList<>();
    private static int accountCount = 0;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Register (new customer with first account)");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = in.nextLine().trim();

            try {
                switch (choice) {
                    case "1": register(); break;
                    case "2": login(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye.");
    }

    private static String ask(String label) {
        System.out.print(label + ": ");
        return in.nextLine().trim();
    }

    private static void pause() {
        System.out.print("\nPress Enter to continue...");
        in.nextLine();
    }

    private static Customer findByUsername(String username) {
        for (Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                return c;
            }
        }
        return null;
    }

    private static Account findAccountOf(Customer c, String accountNumber) {
        for (Account a : c.getAccounts()) {
            if (a.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return a;
            }
        }
        return null;
    }

    private static void register() {
        String name = ask("Name");
        String address = ask("Address");
        String phone = ask("Phone");
        String username = ask("Username");
        if (findByUsername(username) != null) {
            System.out.println("That username is already taken.");
            return;
        }
        String password = ask("Password");

        String id = String.format("C%03d", customers.size() + 1);
        Customer customer = new Customer(id, name, address, phone, username, password);

        System.out.println("\nNow open your first account:");
        Account account = createAccount(customer);
        if (account == null) {
            System.out.println("Registration cancelled.");
            return;
        }

        customer.addAccount(account);
        customers.add(customer);

        System.out.println("\nRegistered successfully.");
        showCustomer(customer);
        pause();
    }

// POLYMORPHISM 
    private static Account createAccount(Customer c) {
        System.out.println("1. Savings  2. Investment  3. Cheque");
        String type = ask("Account type");
        if (!type.equals("1") && !type.equals("2") && !type.equals("3")) {
            System.out.println("Invalid account type.");
            return null;
        }
        String branch = ask("Branch");

        Account account;
        if (type.equals("1")) {
            account = new SavingsAccount(accountNumber("SAV"), branch, c);
            askOptionalDeposit(account);
        } else if (type.equals("2")) {
            double opening = Double.parseDouble(ask("Opening deposit (min 500)"));
            account = new InvestmentAccount(accountNumber("INV"), branch, c, opening);
        } else {
            String employer = ask("Employer name");
            String employerAddress = ask("Employer address");
            if (employer.isEmpty() || employerAddress.isEmpty()) {
                throw new IllegalArgumentException("Cheque accounts need the employer name and address.");
            }
            account = new ChequeAccount(accountNumber("CHQ"), branch, c, employer, employerAddress);
            askOptionalDeposit(account);
        }
        accountCount++;
        System.out.println(account.getAccountType() + " account " + account.getAccountNumber() + " opened.");
        return account;
    }

    private static String accountNumber(String prefix) {
        return String.format("%s-%03d", prefix, accountCount + 1);
    }

    private static void askOptionalDeposit(Account account) {
        String text = ask("Opening deposit (press Enter to skip)");
        if (!text.isEmpty()) {
            account.deposit(Double.parseDouble(text), "Opening deposit");
        }
    }

    private static void login() {
        String username = ask("Username");
        String password = ask("Password");
        Customer c = findByUsername(username);
        if (c == null || !c.verifyPassword(password)) {
            System.out.println("Invalid username or password.");
            return;
        }
        session(c);
    }

    private static void session(Customer c) {
        System.out.println("\nWelcome, " + c.getName() + "!");
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- " + c.getName() + " ---");
            System.out.println("1. View my details and accounts");
            System.out.println("2. Open another account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Pay interest");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String choice = in.nextLine().trim();

            try {
                switch (choice) {
                    case "1": showCustomer(c); pause(); break;
                    case "2": openAnotherAccount(c); break;
                    case "3": deposit(c); break;
                    case "4": withdraw(c); break;
                    case "5": payInterest(c); break;
                    case "0":
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showCustomer(Customer c) {
        System.out.println("\nCustomer:");
        System.out.println("  ID:       " + c.getCustomerId());
        System.out.println("  Name:     " + c.getName());
        System.out.println("  Address:  " + c.getAddress());
        System.out.println("  Phone:    " + c.getPhone());
        System.out.println("  Username: " + c.getUsername());
        for (Account a : c.getAccounts()) {
            System.out.printf("%nAccount %s (%s), branch %s, balance %.2f%n",
                    a.getAccountNumber(), a.getAccountType(), a.getBranch(), a.getBalance());
            for (Transaction t : a.getTransactions()) {
                System.out.println("  " + t);
            }
        }
    }

    private static void openAnotherAccount(Customer c) {
        Account account = createAccount(c);
        if (account != null) {
            c.addAccount(account);
        }
    }

    private static Account chooseAccount(Customer c) {
        System.out.println("Your accounts:");
        for (Account a : c.getAccounts()) {
            System.out.printf("  %s (%s) balance %.2f%n",
                    a.getAccountNumber(), a.getAccountType(), a.getBalance());
        }
        Account a = findAccountOf(c, ask("Account number"));
        if (a == null) {
            System.out.println("Account not found.");
        }
        return a;
    }

    private static void deposit(Customer c) {
        Account a = chooseAccount(c);
        if (a == null) {
            return;
        }
        double amount = Double.parseDouble(ask("Amount"));
        a.deposit(amount);
        System.out.printf("Deposited. New balance: %.2f%n", a.getBalance());
    }

    private static void withdraw(Customer c) {
        Account a = chooseAccount(c);
        if (a == null) {
            return;
        }
        boolean ok;
        if (a instanceof InvestmentAccount) {
            ok = ((InvestmentAccount) a).withdraw(Double.parseDouble(ask("Amount")));
        } else if (a instanceof ChequeAccount) {
            ok = ((ChequeAccount) a).withdraw(Double.parseDouble(ask("Amount")));
        } else {
            System.out.println("Withdrawals are not allowed on this account type.");
            return;
        }
        if (ok) {
            System.out.printf("Withdrawn. New balance: %.2f%n", a.getBalance());
        } else {
            System.out.println("Withdrawal failed (invalid amount or insufficient funds).");
        }
    }

    private static void payInterest(Customer c) {
        Account a = chooseAccount(c);
        if (a == null) {
            return;
        }
        if (a instanceof InterestCalculations) {
            ((InterestCalculations) a).payInterest();
            System.out.printf("Interest paid. New balance: %.2f%n", a.getBalance());
        } else {
            System.out.println("This account type does not earn interest.");
        }
    }
}
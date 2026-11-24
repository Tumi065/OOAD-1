package BankingSystemOOAD;

public class LoginController {

    public boolean login(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {

            System.out.println("Username or password is empty.");
            return false;
        }

        System.out.println("Login successful.");

        return true;
    }
}

package BankingSystemOOAD;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        try {

            Connection conn =
                    DatabaseConnection.getConnection();

            Statement stmt =
                    conn.createStatement();

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Customers (" +
                    "CustomerID TEXT PRIMARY KEY," +
                    "Name TEXT NOT NULL," +
                    "Address TEXT," +
                    "Phone TEXT," +
                    "Username TEXT UNIQUE)"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Accounts (" +
                    "AccountNumber TEXT PRIMARY KEY," +
                    "CustomerID TEXT," +
                    "AccountType TEXT," +
                    "Branch TEXT," +
                    "Balance REAL," +
                    "FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID))"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Transactions (" +
                    "TransactionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "AccountNumber TEXT," +
                    "Amount REAL," +
                    "TransactionType TEXT," +
                    "FOREIGN KEY(AccountNumber) REFERENCES Accounts(AccountNumber))"
            );

            System.out.println(
                    "Database initialized successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
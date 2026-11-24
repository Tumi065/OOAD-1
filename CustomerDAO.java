package BankingSystemOOAD;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {

    public void saveCustomer(Customer customer) {

        String sql =
                "INSERT INTO Customers " +
                "(CustomerID, Name, Address, Phone, Username) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    customer.getCustomerId()
            );

            ps.setString(
                    2,
                    customer.getName()
            );

            ps.setString(
                    3,
                    customer.getAddress()
            );

            ps.setString(
                    4,
                    customer.getPhone()
            );

            ps.setString(
                    5,
                    customer.getUsername()
            );

            ps.executeUpdate();

            System.out.println(
                    "Customer saved successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
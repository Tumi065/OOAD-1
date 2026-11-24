package BankingSystemOOAD;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public void start(Stage stage) {

        AccountController controller =
                new AccountController();

        Button viewAccounts =
                new Button("View Accounts");

        Button openAccount =
                new Button("Open Account");

        Button deposit =
                new Button("Deposit");

        Button withdraw =
                new Button("Withdraw");

        Button payInterest =
                new Button("Pay Interest");

        Button logout =
                new Button("Logout");

        Button back =
                new Button("Back");

        viewAccounts.setOnAction(e -> {

            controller.viewAccount();

            Stage s = new Stage();
            new AccountView().start(s);
        });

        openAccount.setOnAction(e ->
                controller.openAccount());

        deposit.setOnAction(e ->
                controller.deposit(100));

        withdraw.setOnAction(e ->
                controller.withdraw(50));

        payInterest.setOnAction(e ->
                controller.payInterest());

        logout.setOnAction(e ->
                stage.close());

        back.setOnAction(e ->
                stage.close());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                viewAccounts,
                openAccount,
                deposit,
                withdraw,
                payInterest,
                logout,
                back
        );

        Scene scene =
                new Scene(root, 350, 300);

        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
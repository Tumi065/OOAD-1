package BankingSystemOOAD;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountView {

    public void start(Stage stage) {

        Label title = new Label("Account Information");

        Label accountNumber =
                new Label("Account: SAV-001");

        Label accountType =
                new Label("Type: Savings");

        Label branch =
                new Label("Branch: Main");

        Label balance =
                new Label("Balance: P0.00");

        Button back = new Button("Back");

        back.setOnAction(e -> stage.close());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                accountNumber,
                accountType,
                branch,
                balance,
                back
        );

        Scene scene = new Scene(root, 350, 250);

        stage.setTitle("Account Details");
        stage.setScene(scene);
        stage.show();
    }
}
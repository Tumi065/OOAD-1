package BankingSystemOOAD;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingGUI extends Application {

    @Override
    public void start(Stage stage) {

        Button loginBtn =
                new Button("Login Screen");

        Button registerBtn =
                new Button("Register Screen");

        Button dashboardBtn =
                new Button("Dashboard");

        Button accountBtn =
                new Button("Account Details");

        loginBtn.setOnAction(e -> {

            Stage s = new Stage();

            new LoginView().start(s);
        });

        registerBtn.setOnAction(e -> {

            Stage s = new Stage();

            new RegisterView().start(s);
        });

        dashboardBtn.setOnAction(e -> {

            Stage s = new Stage();

            new DashboardView().start(s);
        });

        accountBtn.setOnAction(e -> {

            Stage s = new Stage();

            new AccountView().start(s);
        });

        VBox root = new VBox(10);

        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                loginBtn,
                registerBtn,
                dashboardBtn,
                accountBtn
        );

        Scene scene =
                new Scene(root, 300, 250);

        stage.setTitle(
                "Banking GUI Launcher"
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
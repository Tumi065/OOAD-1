package BankingSystemOOAD;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    public void start(Stage stage) {

        Label title = new Label("Banking System Login");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefWidth(180);
        username.setMaxWidth(180);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefWidth(180);
        password.setMaxWidth(180);

        Button login = new Button("Login");
        Button back = new Button("Back");

        login.setOnAction(e -> {

            LoginController controller =
                    new LoginController();

            controller.login(
                    username.getText(),
                    password.getText()
            );
        });

        back.setOnAction(e -> stage.close());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                username,
                password,
                login,
                back
        );

        Scene scene = new Scene(root, 350, 250);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
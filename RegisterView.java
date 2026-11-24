package BankingSystemOOAD;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    public void start(Stage stage) {

        Label title = new Label("Register Customer");

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setMaxWidth(180);

        TextField address = new TextField();
        address.setPromptText("Address");
        address.setMaxWidth(180);

        TextField phone = new TextField();
        phone.setPromptText("Phone");
        phone.setMaxWidth(180);

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(180);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(180);

        Button register = new Button("Register");
        Button back = new Button("Back");

        register.setOnAction(e -> {

            CustomerController controller =
                    new CustomerController();

            controller.registerCustomer(
                    name.getText(),
                    address.getText(),
                    phone.getText(),
                    username.getText(),
                    password.getText()
            );
        });

        back.setOnAction(e -> stage.close());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                name,
                address,
                phone,
                username,
                password,
                register,
                back
        );

        Scene scene = new Scene(root, 400, 400);

        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
}
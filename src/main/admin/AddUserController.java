package main.admin;

import database.ClientTable;
import database.UserTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddUserController {
    @FXML
    public PasswordField password;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField patronymic;
    @FXML
    public TextField position;
    @FXML
    private TextField username;
    public void toBack(ActionEvent actionEvent) {
    }

    public void toHome(ActionEvent actionEvent) {
    }

    public void aboutUs(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void addNew(ActionEvent actionEvent) {
        System.out.println(username.getText());
        System.out.println(password.getText());
        System.out.println(firstName.getText());
        System.out.println(lastName.getText());
        System.out.println(patronymic.getText());
        System.out.println(position.getText());

        System.out.println(new UserTable().createNew(username.getText(),
                password.getText(),
                firstName.getText(),
                lastName.getText(),
                patronymic.getText(),
                position.getText(),
                true));

    }
}

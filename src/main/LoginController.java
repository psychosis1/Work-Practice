package main;

import database.UserTable;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import properties.Current;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button enter;

    @FXML
    private void login(ActionEvent actionEvent) throws IOException {
        if (new UserTable().selectUser(username.getText(), password.getText()) == 0) {
            Application.stage(getClass(),(Stage)enter.getScene().getWindow(),"../fxml/general.fxml","Главная страница");
            saveUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка входа в систему");
            alert.setContentText("Неправильный логин или пароль");
            alert.show();
        }
    }

    private void saveUser() {
        try {
            User.save("user.dat", Current.USER);
        }catch (IOException error){
            error.printStackTrace();
        }
    }
}

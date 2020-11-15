package main;

import database.UserTable;
import implementation.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties.Current;
import properties.Properties;

import java.io.IOException;

import static main.Main.toGeneral;

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
            toGeneral((Stage)enter.getScene().getWindow(),getClass());
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

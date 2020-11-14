package main;

import database.UserTable;
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
import properties.Properties;

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
            toGeneral();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка входа в систему");
            alert.setContentText("Неправильный логин или пароль");
            alert.show();
        }
    }

    private void toGeneral() throws IOException {
        Stage stage = (Stage) enter.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/general.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(Properties.ICON);
        stage.setTitle("Главная страница");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

package main.admin;

import database.ClientTable;
import database.UserTable;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Alerts;
import main.Application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    @FXML
    private ComboBox<String> root;

    @FXML
    public void initialize() {
        root.setItems(FXCollections.observableList(Arrays.asList((new String[] {"администратор","стандартные"}).clone())));
        root.setValue("стандартные");
    }

    public void toBack(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    public void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/general.fxml", "Главная страница");
    }

    public void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    public void addNew(ActionEvent actionEvent) throws IOException {
        if(username.getText().equals("")){
            Alerts.warning( "Не заполнено обязательное поле!", "Поле 'логин' должно быть заполнено.");
        }else if(password.getText().equals("")){
            Alerts.warning( "Не заполнено обязательное поле!", "Поле 'пароль' должно быть заполнено.");
        }else {
            if(new UserTable().createNew(username.getText(),
                    password.getText(),
                    firstName.getText(),
                    lastName.getText(),
                    patronymic.getText(),
                    position.getText(),
                    root.getValue().equals("администратор"))){
                Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
            }else {
                Alerts.warning( "Что-то пошло не так!", "Возможно пользователь с таким логином уже существует.");
            }
        }




    }
}

package main.admin;

import database.UserTable;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Alerts;
import main.Application;
import properties.Current;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RefactorUserController {
    @FXML
    public TextField username;
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
    public ComboBox<String> root;

    @FXML
    public void initialize() {
        root.setItems(FXCollections.observableList(Arrays.asList((new String[] {"администратор","стандартные"}).clone())));

        username.setText(Current.REFACTOR_USER.getUsername());
        firstName.setText(Current.REFACTOR_USER.getFirst_name());
        lastName.setText(Current.REFACTOR_USER.getLast_name());
        patronymic.setText(Current.REFACTOR_USER.getPatronymic());
        position.setText(Current.REFACTOR_USER.getPosition());
        root.setValue((Current.REFACTOR_USER.isAdmin())?"администратор":"стандартные");

    }

    @FXML
    public void toBack(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER=null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    @FXML
    public void toHome(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER=null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/general.fxml", "Главная страница");
    }

    @FXML
    public void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    @FXML
    public void changeUsername(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"username",username.getText())) {
            Alerts.info("Информация", "Успешно!", "Значение логина изменено.");
            Current.REFACTOR_USER.setUsername(username.getText());
        }
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так. Возможно такой логин существует.");
    }

    @FXML
    public void changePassword(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"password",password.getText()))
            Alerts.info("Информация","Успешно!","Значение пароля изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void changeFirstName(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"first_name",firstName.getText()))
            Alerts.info("Информация","Успешно!","Значение имени изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void changeLastName(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"last_name",lastName.getText()))
            Alerts.info("Информация","Успешно!","Значение фамилии изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void changePatronymic(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"patronymic",patronymic.getText()))
            Alerts.info("Информация","Успешно!","Значение отчества изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void changePosition(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"position",position.getText()))
            Alerts.info("Информация","Успешно!","Значение должности изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void changeAdmin(ActionEvent actionEvent) {
        if(new UserTable().updateField(Current.REFACTOR_USER.getUsername(),"admin",(root.getValue().equals("администратор"))?"1":"0"))
            Alerts.info("Информация","Успешно!","Значение прав изменено.");
        else Alerts.warning("Изменение не удалось!","Что-то пошло не так.");
    }

    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER=null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        if(new UserTable().deleteUser(Current.REFACTOR_USER.getUsername())){
            Current.REFACTOR_USER=null;
            Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
        } else Alerts.warning("Удаление не удалось!","Что-то пошло не так.");

    }
}

package main;

import database.UserTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import properties.Current;

import java.io.IOException;
import java.util.Optional;


public class ProfileController {

    private final UserTable table = new UserTable();

    private String value;

    @FXML
    private TextField username, firstName, lastName, patronymic, position;

    @FXML
    private PasswordField password;

    @FXML
    public void initialize() {
        username.setText(Current.USER.getUsername());
        firstName.setText(Current.USER.getFirst_name());
        lastName.setText(Current.USER.getLast_name());
        patronymic.setText(Current.USER.getPatronymic());
        position.setText(Current.USER.getPosition());
    }

    private void changePassword() {
        PasswordDialog dialog = new PasswordDialog();
        change(dialog, "Смена пароля", "Новый пароль:");

    }

    private void changeNotPassword(String header, String content) {
        TextInputDialog dialog = new TextInputDialog();
        change(dialog, header, content);
    }


    private void change(Dialog<String> dialog, String header, String content) {
        dialog.setTitle("Изменение профиля");
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        Alerts.setIcon(dialog);

        Optional<String> field = dialog.showAndWait();

        field.ifPresentOrElse(
                (value)
                        -> this.value = value,
                ()
                        -> this.value = "");
    }

    @FXML
    private void setUsername(ActionEvent actionEvent) {
        changeNotPassword("Смена логина", "Новый логин");
        if (!value.isEmpty()) {
            if (usernameValue()) {
                if (table.update("username", value) != -1) {
                    Current.USER.setUsername(value);
                    username.setText(value);
                } else Alerts.error("Ошибка смены логина", "Такой логин уже существует.");
            } else Alerts.error("Ошибка смены логина", "Логин должен включать как миниму 3 знака. " +
                    "Может состоять из цифр, букв, знаков: - _ .\nТакже не должен содержать пробелы и должен начинаться с буквы.", 400);
        }
    }

    public boolean usernameValue() {
        return value.matches("\\b[a-z-а-я-A-Z-А-Я][a-z-а-я-A-Z-А-Я-0-9\\-._]{3,}\\b");
    }

    @FXML
    private void setPassword(ActionEvent actionEvent) {
        if (!password.getText().equals(Current.USER.getPassword())) {
            Alerts.error("Ошибка смены пароля", "Пароль не совпадает с текущим.");
        } else {
            changePassword();
            if (!value.isEmpty()) {
                if (passwordValue()) {
                    if (table.update("password", value) != -1) Current.USER.setPassword(value);
                } else Alerts.error("Ошибка смены пароля", "Пароль должен включать как миниму: 8 знаков," +
                        " 1 большую букву, 1 маленькую букву, 1 цифру.\nТакже не должен содержать пробелы", 400);
            }
        }
    }

    public boolean passwordValue() {
        return value.matches("^(?=.*\\d)(?=.*[a-z-a-я])(?=.*[A-Z-А-Я]).{8,}$");
    }

    @FXML
    private void setFirstName(ActionEvent actionEvent) {
        changeNotPassword("Смена имени", "Новое имя");
        if (!value.isEmpty()) {
            if (table.update("first_name", value) != -1) {
                Current.USER.setFirst_name(value);
                firstName.setText(value);
            }
        }
    }

    @FXML
    private void setLastName(ActionEvent actionEvent) {
        changeNotPassword("Смена фамилии", "Новая фамилия");
        if (!value.isEmpty()) {
            if (table.update("last_name", value) != -1) {
                Current.USER.setLast_name(value);
                lastName.setText(value);
            }
        }
    }

    @FXML
    private void setPatronymic(ActionEvent actionEvent) {
        changeNotPassword("Смена отчества", "Новое отчество");
        if (!value.isEmpty()) {
            if (table.update("patronymic", value) != -1) {
                Current.USER.setPatronymic(value);
                patronymic.setText(value);
            }
        }
    }

    @FXML
    private void setPosition(ActionEvent actionEvent) {
        changeNotPassword("Смена должности", "Новая должность");
        if (!value.isEmpty()) {
            if (table.update("position", value) != -1) {
                Current.USER.setPosition(value);
                position.setText(value);
            }
        }
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(), (Stage) username.getScene().getWindow());
    }

    @FXML
    private void toAdmin(ActionEvent actionEvent) {

    }

    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../fxml/general.fxml", "Главная страница");
    }
}

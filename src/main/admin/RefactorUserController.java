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

    /**
     * Проверка пароля на корректность
     *
     * @param value - строка для проверки
     * @return - ответ проверки (true/false)
     */
    public boolean passwordValue(String value) {
        return value.matches("^(?=.*\\d)(?=.*[a-z-a-я])(?=.*[A-Z-А-Я]).{8,}$");
    }

    /**
     * Проверка логина на корректность
     *
     * @param value - строка для проверки
     * @return - ответ проверки (true/false)
     */
    public boolean usernameValue(String value) {
        return value.matches("\\b[a-z-а-я-A-Z-А-Я][a-z-а-я-A-Z-А-Я-0-9\\-._]{3,}\\b");
    }

    /**
     * Конструктор окна
     */
    @FXML
    public void initialize() {
        root.setItems(FXCollections.observableList(Arrays.asList((new String[]{"администратор", "стандартные"}).clone())));

        username.setText(Current.REFACTOR_USER.getUsername());
        firstName.setText(Current.REFACTOR_USER.getFirst_name());
        lastName.setText(Current.REFACTOR_USER.getLast_name());
        patronymic.setText(Current.REFACTOR_USER.getPatronymic());
        position.setText(Current.REFACTOR_USER.getPosition());
        root.setValue((Current.REFACTOR_USER.isAdmin()) ? "администратор" : "стандартные");

    }

    /**
     * Переход на предыдущую страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    public void toBack(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER = null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    /**
     * Переход на домашнюю страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    public void toHome(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER = null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/general.fxml", "Главная страница");
    }

    /**
     * Открытие окна с описанием компании
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    /**
     * функция смены поля "username" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changeUsername(ActionEvent actionEvent) {
        if (usernameValue(username.getText()) && !username.getText().equals("")) {
            if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "username", username.getText())) {
                Alerts.info("Информация", "Успешно!", "Значение логина изменено.");
                Current.REFACTOR_USER.setUsername(username.getText());
                Current.USER.setUsername(username.getText());
            } else Alerts.warning("Изменение не удалось!", "Что-то пошло не так. Возможно такой логин существует.");
        } else
            Alerts.warning("Не заполнено или заполнено неправильно поле!", "Логин должен включать как миниму 3 знака. " +
                    "Может состоять из цифр, букв, знаков: - _ .\nТакже не должен содержать пробелы и должен начинаться с буквы.");
    }

    /**
     * функция смены поля "password" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changePassword(ActionEvent actionEvent) {
        if (passwordValue(password.getText()) && !password.getText().equals("")) {
            if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "password", password.getText()))
                Alerts.info("Информация", "Успешно!", "Значение пароля изменено.");
            else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
        } else
            Alerts.warning("Не заполнено или заполнено неправильно поле!", "Пароль должен включать как миниму: 8 знаков," +
                    " 1 большую букву, 1 маленькую букву, 1 цифру.\nТакже не должен содержать пробелы");

    }

    /**
     * функция смены поля "first_name" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changeFirstName(ActionEvent actionEvent) {
        if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "first_name", firstName.getText()))
            Alerts.info("Информация", "Успешно!", "Значение имени изменено.");
        else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
    }

    /**
     * функция смены поля "last_name" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changeLastName(ActionEvent actionEvent) {
        if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "last_name", lastName.getText()))
            Alerts.info("Информация", "Успешно!", "Значение фамилии изменено.");
        else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
    }

    /**
     * функция смены поля "patronymic" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changePatronymic(ActionEvent actionEvent) {
        if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "patronymic", patronymic.getText()))
            Alerts.info("Информация", "Успешно!", "Значение отчества изменено.");
        else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
    }

    /**
     * функция смены поля "position" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changePosition(ActionEvent actionEvent) {
        if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "position", position.getText()))
            Alerts.info("Информация", "Успешно!", "Значение должности изменено.");
        else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
    }

    /**
     * функция смены поля "admin" у пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void changeAdmin(ActionEvent actionEvent) {
        if (new UserTable().updateField(Current.REFACTOR_USER.getUsername(), "admin", (root.getValue().equals("администратор")) ? "1" : "0"))
            Alerts.info("Информация", "Успешно!", "Значение прав изменено.");
        else Alerts.warning("Изменение не удалось!", "Что-то пошло не так.");
    }

    /**
     * Переход на предыдущую страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        Current.REFACTOR_USER = null;
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
    }

    /**
     * Функция удаления пользователя из бд
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        if (new UserTable().deleteUser(Current.REFACTOR_USER.getUsername()) && !Current.REFACTOR_USER.getUsername().equals(Current.USER.getUsername())) {
            Current.REFACTOR_USER = null;
            Application.stage(getClass(), (Stage) username.getScene().getWindow(), "../../fxml/userList.fxml", "Администрирование");
        } else Alerts.warning("Удаление не удалось!", "Что-то пошло не так.");

    }
}

package main.admin;

import database.ClientTable;
import database.UserTable;
import entity.User;
import fxml.FXMLURL;
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
        root.setValue("стандартные");
    }

    /**
     * Переход на предыдущую страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    public void toBack(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), new FXMLURL().getUserList(), "Администрирование");
    }

    /**
     * Переход на домашнюю страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    public void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), new FXMLURL().getGeneral(), "Главная страница");
    }

    /**
     * Открытие окна с описанием компании
     *
     * @param actionEvent - сгенерированное событие
     */
    public void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    /**
     * Переход на предыдущую страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) username.getScene().getWindow(), new FXMLURL().getUserList(), "Администрирование");
    }

    /**
     * Добавление пользователя в базу данных
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    public void addNew(ActionEvent actionEvent) throws IOException {
        if (username.getText().equals("") || !usernameValue(username.getText())) {
            Alerts.warning("Не заполнено или заполнено неправильно поле!", "Логин должен включать как миниму 3 знака. " +
                    "Может состоять из цифр, букв, знаков: - _ .\nТакже не должен содержать пробелы и должен начинаться с буквы.");
        } else if (password.getText().equals("") || !passwordValue(password.getText())) {
            Alerts.warning("Не заполнено или заполнено неправильно поле!", "Пароль должен включать как миниму: 8 знаков," +
                    " 1 большую букву, 1 маленькую букву, 1 цифру.\nТакже не должен содержать пробелы");
        } else {
            if (new UserTable().createNew(username.getText(),
                    password.getText(),
                    firstName.getText(),
                    lastName.getText(),
                    patronymic.getText(),
                    position.getText(),
                    root.getValue().equals("администратор"))) {
                Application.stage(getClass(), (Stage) username.getScene().getWindow(), new FXMLURL().getUserList(), "Администрирование");
            } else {
                Alerts.warning("Что-то пошло не так!", "Возможно пользователь с таким логином уже существует.");
            }
        }
    }
}

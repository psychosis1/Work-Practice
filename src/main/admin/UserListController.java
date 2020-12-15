package main.admin;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import database.ClientTable;
import database.UserTable;
import entity.Client;
import entity.User;
import fxml.FXMLURL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Alerts;
import main.Application;
import properties.Current;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserListController {
    @FXML
    private MenuBar menu;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> full_name,login,position;

    @FXML
    private TextField search;

    private FilteredList<User> filteredData;

    /**
     * Конструктор окна
     */
    @FXML
    public void initialize() {

        List<User> list = new UserTable().getUserList();
        ObservableList<User> users = FXCollections.observableList(list);
        table.setItems(users);
        System.out.println(list.get(1).getUsername());
        full_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        login.setCellValueFactory(new PropertyValueFactory<>("username"));
        filteredData = new FilteredList<>(users, p -> true);
    }

    /**
     * функция поиска совпадений и перерисовки таблицы
     * @param inputEvent - сгенерированное событие ввода
     */
    @FXML
    public void find(Event inputEvent) {
        filteredData.setPredicate(myObject -> {
            if (search.getText() == null || search.getText().isEmpty()) {
                return true;
            }
            String lowerCaseFilter = search.getText().toLowerCase();
            System.out.println(lowerCaseFilter);
            if (String.valueOf(myObject.getUsername()).toLowerCase().contains(lowerCaseFilter)) {
                return true;

            }else
            if (String.valueOf(myObject.getPosition()).toLowerCase().contains(lowerCaseFilter)) {
                return true;

            } else return String.valueOf(myObject.getFullName()).toLowerCase().contains(lowerCaseFilter);
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    /**
     * Переход на домашнюю страницу
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) search.getScene().getWindow(), new FXMLURL().getGeneral(), "Главная страница");
    }

    /**
     * Открытие окна с описанием компании
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    /**
     * Разавторизация и открытие окна с авторизацией
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(), (Stage) search.getScene().getWindow(),new FXMLURL().getLogin());
    }

    /**
     * Открытие окна с редактированием данных текущего пользователя
     *
     * @param actionEvent - сгенерированное событие
     * @throws IOException - исключения ввода/вывода
     */
    @FXML
    private void toProfile(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) search.getScene().getWindow(), new FXMLURL().getProfile(), "Профиль");
    }

    /**
     * Открытие окна с добавлением нового пользователя
     *
     * @param actionEvent - сгенерированное событие
     */
    @FXML
    public void addNew(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) search.getScene().getWindow(), new FXMLURL().getAddUser(), "Добавление нового пользователя");
    }

    /**
     * Открытие окна с редактированием данных, существующего пользователя
     *
     * @param mouseEvent - сгенерированное событие мыши
     */
    @FXML
    public void clickRow(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) //Проверка на двойной клик
        {
            if(!table.getSelectionModel().getSelectedItem().getUsername().equals(Current.USER.getUsername())){
            Current.REFACTOR_USER = table.getSelectionModel().getSelectedItem();
            Application.stage(getClass(), (Stage) search.getScene().getWindow(), new FXMLURL().getRefactorUser(), "Работа с пользователем");
            }else Alerts.warning("Не удалось открыть!", "Текущий аккаунт можно редактировать только в профиле.");
        }
    }
}

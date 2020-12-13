package main.admin;

import database.ClientTable;
import database.UserTable;
import entity.Client;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Alerts;
import main.Application;

import java.io.IOException;
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

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) search.getScene().getWindow(), "../../fxml/general.fxml", "Главная страница");
    }

    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    public void addNew(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) search.getScene().getWindow(), "../../fxml/addUser.fxml", "Добавление нового пользователя");
    }
}

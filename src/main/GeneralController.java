package main;

import database.ClientTable;
import entity.Client;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import properties.Current;

import java.io.IOException;

public class GeneralController {
    @FXML
    private MenuBar menu;

    @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<Client, String> code, full_name;

    @FXML
    private TextField search;

    private FilteredList<Client> filteredData;

    @FXML
    public void initialize() {
        ObservableList<Client> clients = FXCollections.observableList(new ClientTable().selectClientsForGeneral());
        table.setItems(clients);
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        full_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        filteredData = new FilteredList<>(clients, p -> true);
    }

    @FXML
    public void clickRow(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) //Проверка на двойной клик
        {
            Current.CLIENT = table.getSelectionModel().getSelectedItem();
            Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../fxml/client.fxml", "Работа с клиентом");
        }
    }

    @FXML
    public void find(Event inputEvent) {
        filteredData.setPredicate(myObject -> {
            if (search.getText() == null || search.getText().isEmpty()) {
                return true;
            }
            String lowerCaseFilter = search.getText().toLowerCase();
            if (String.valueOf(myObject.getCode()).toLowerCase().contains(lowerCaseFilter)) {
                return true;

            } else return String.valueOf(myObject.getFull_name()).toLowerCase().contains(lowerCaseFilter);
        });

        SortedList<Client> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(), (Stage) menu.getScene().getWindow());
    }

    @FXML
    private void toProfile(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../fxml/profile.fxml", "Профиль");
    }

    @FXML
    private void toAdmin(ActionEvent actionEvent) {

    }

    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    @FXML
    private void toClient(ActionEvent actionEvent) throws IOException {
        Current.CLIENT = null;
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../fxml/client.fxml", "Работа с клиентом");
    }
}

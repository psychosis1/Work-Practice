package main;

import database.ClientTable;
import implementation.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties.Current;
import properties.Properties;

import java.io.IOException;

public class GeneralController extends MenuBarMethods {
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
    public void clickRow(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Проверка на двойной клик
        {
            Current.CLIENT = table.getSelectionModel().getSelectedItem();
            System.out.println(Current.CLIENT);
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
        super.logout(menu);
    }

    @FXML
    private void toProfile(ActionEvent actionEvent) {

    }

    @FXML
    private void toAdmin(ActionEvent actionEvent) {

    }

    @FXML
    private void toClient(ActionEvent actionEvent) throws IOException {
        super.stage(menu,"../fxml/client.fxml","Работа с клиентом");
    }
}

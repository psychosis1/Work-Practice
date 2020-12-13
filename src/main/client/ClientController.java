package main.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Alerts;
import main.Application;
import properties.Current;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController {
    @FXML
    private MenuBar menu;
    @FXML
    private Label clientForm, testBoyko, testGAGE, testSOCRATES, typologicalGroups;

    @FXML
    private AnchorPane workArea;

    private List<Label> labels;

    @FXML
    public void initialize() {
        labels = new ArrayList<>(Arrays.asList(clientForm, testBoyko, testGAGE, testSOCRATES, typologicalGroups));
    }

    public void setColorForLabel(MouseEvent mouseEvent) {
        Label labelClicked = (Label) mouseEvent.getSource();
        for (Label label : labels) {
            if (label.getId().equals(labelClicked.getId()))
                label.setTextFill(Color.CHOCOLATE);
            else
                label.setTextFill(Color.BLACK);
        }
    }

    public void updateWorkArea(Pane pane) {
        if (workArea.getChildren().size() > 0)
            workArea.getChildren().remove(0);
        workArea.getChildren().add(pane);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        pane.setPrefWidth(100);
        pane.setPrefHeight(160);
    }

    @FXML
    private void toClientForm(MouseEvent mouseEvent) throws IOException {
        setColorForLabel(mouseEvent);
        updateWorkArea(FXMLLoader.load(getClass().getResource("../../fxml/clientForm.fxml")));
    }

    @FXML
    private void toTestBoyko(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(getClass().getResource("../../fxml/testBoyko.fxml")));
        }
    }

    @FXML
    private void toTestGAGE(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(getClass().getResource("../../fxml/testGAGE.fxml")));
        }
    }

    @FXML
    private void toTypologicalGroups(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(getClass().getResource("../../fxml/typologicalGroup.fxml")));
        }
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(), (Stage) menu.getScene().getWindow());
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../../fxml/general.fxml", "Главная страница");
    }

    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    @FXML
    private void toAdmin(ActionEvent actionEvent) throws IOException {
        if(Current.USER.isAdmin())
            Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../fxml/userList.fxml", "Администрирование");
        else Alerts.warning("Недостаточно прав!","Данный аккаунт не имеет прав администратора.");

    }

    @FXML
    private void toProfile(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), "../../fxml/profile.fxml", "Профиль");
    }

}

package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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

    private void setColorForLabel(MouseEvent mouseEvent) {
        Label labelClicked = (Label) mouseEvent.getSource();
        for (Label label : labels) {
            if (label.getId().equals(labelClicked.getId()))
                label.setTextFill(Color.CHOCOLATE);
            else
                label.setTextFill(Color.BLACK);
        }
    }

    private void updateWorkArea(Pane pane) {
        if (workArea.getChildren().size()>0)
            workArea.getChildren().remove(0);
        workArea.getChildren().add(pane);
        AnchorPane.setBottomAnchor(pane,0.0);
        AnchorPane.setLeftAnchor(pane,0.0);
        AnchorPane.setRightAnchor(pane,0.0);
        AnchorPane.setTopAnchor(pane,0.0);
        pane.setPrefWidth(100);
        pane.setPrefHeight(160);
    }

    @FXML
    private void toClientForm(MouseEvent mouseEvent) throws IOException {
        setColorForLabel(mouseEvent);
        updateWorkArea(FXMLLoader.load(getClass().getResource("../fxml/clientForm.fxml")));
    }

    @FXML
    private void toTestGAGE(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT!=null){
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(getClass().getResource("../fxml/testGAGE.fxml")));
        }
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(),(Stage) menu.getScene().getWindow());
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(),(Stage) menu.getScene().getWindow(),"../fxml/general.fxml","Главная страница");
    }

}

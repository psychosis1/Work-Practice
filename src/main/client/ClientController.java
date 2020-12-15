package main.client;

import fxml.FXMLURL;
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

    /**
     * Инициализация окна
     */
    @FXML
    public void initialize() {
        labels = new ArrayList<>(Arrays.asList(clientForm, testBoyko, testGAGE, testSOCRATES, typologicalGroups));
    }

    /**
     * Установка цвета для выбранного в списке пункта
     *
     * @param mouseEvent Движение мыши
     */
    public void setColorForLabel(MouseEvent mouseEvent) {
        Label labelClicked = (Label) mouseEvent.getSource();
        for (Label label : labels) {
            if (label.getId().equals(labelClicked.getId()))
                label.setTextFill(Color.CHOCOLATE);
            else
                label.setTextFill(Color.BLACK);
        }
    }

    /**
     * Обновление рабочей области
     *
     * @param pane Рабочая область
     */
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

    /**
     * Переход к анкете
     *
     * @param mouseEvent Движение мыши
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toClientForm(MouseEvent mouseEvent) throws IOException {
        setColorForLabel(mouseEvent);
        updateWorkArea(FXMLLoader.load(new FXMLURL().getClientForm()));
    }

    /**
     * Переход к тесту Бойко
     *
     * @param mouseEvent Двежение мыши
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toTestBoyko(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(new FXMLURL().getTestBoyko()));
        }
    }

    /**
     * Переход к тесту GAGE
     *
     * @param mouseEvent Двежение мыши
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toTestGAGE(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(new FXMLURL().getTestGAGE()));
        }
    }

    /**
     * Переход к типологической группе
     *
     * @param mouseEvent Двежение мыши
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toTypologicalGroups(MouseEvent mouseEvent) throws IOException {
        if (Current.CLIENT != null) {
            setColorForLabel(mouseEvent);
            updateWorkArea(FXMLLoader.load(new FXMLURL().getTypologicalGroup()));
        }
    }

    /**
     * Выход из аккаунта
     *
     * @param actionEvent Событие
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Application.logout(getClass(), (Stage) menu.getScene().getWindow(), new FXMLURL().getLogin());
    }

    /**
     * Переход на главную страницу
     *
     * @param actionEvent Событие
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), new FXMLURL().getGeneral(), "Главная страница");
    }

    /**
     * Информация о нас
     *
     * @param actionEvent Событие
     */
    @FXML
    private void aboutUs(ActionEvent actionEvent) {
        Alerts.aboutUs();
    }

    /**
     * Перерход к модулю администрирования
     *
     * @param actionEvent Событие
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toAdmin(ActionEvent actionEvent) throws IOException {
        if (Current.USER.isAdmin())
            Application.stage(getClass(), (Stage) menu.getScene().getWindow(), new FXMLURL().getUserList(), "Администрирование");
        else Alerts.warning("Недостаточно прав!", "Данный аккаунт не имеет прав администратора.");

    }

    /**
     * Переход к профилю пользователя
     *
     * @param actionEvent Событие
     * @throws IOException Ошибка перехода
     */
    @FXML
    private void toProfile(ActionEvent actionEvent) throws IOException {
        Application.stage(getClass(), (Stage) menu.getScene().getWindow(), new FXMLURL().getProfile(), "Профиль");
    }

}

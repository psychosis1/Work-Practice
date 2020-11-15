package main;

import database.ClientTable;
import implementation.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties.Current;
import properties.Properties;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadUser(primaryStage);
    }

    public void loadUser(Stage stage) throws IOException {
        try {
            Current.USER = User.load("user.dat");
            System.out.println(Current.USER);
            main.Application.stage(getClass(), stage, "../fxml/general.fxml", "Главная страница");
        } catch (IOException | ClassNotFoundException error) {
            log.log(Level.WARNING, error.getMessage());
            main.Application.stage(getClass(), stage, "../fxml/login.fxml", "Авторизация");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

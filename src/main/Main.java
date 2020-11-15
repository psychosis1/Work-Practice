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
            toGeneral(stage, getClass());
        } catch (IOException | ClassNotFoundException error) {
            log.log(Level.WARNING, error.getMessage());
            toAuthorization(stage);
        }
    }

    public void toAuthorization(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
        stage.getIcons().add(Properties.ICON);
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void toGeneral(Stage stage, Class<?> object) throws IOException {
        stage.close();
        Parent root = FXMLLoader.load(object.getResource("../fxml/general.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(Properties.ICON);
        stage.setTitle("Главная страница");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

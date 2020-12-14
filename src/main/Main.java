package main;

import entity.User;
import javafx.application.Application;
import javafx.stage.Stage;
import properties.Current;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    /**
     * Начало приложения
     *
     * @param primaryStage Начальная сцена
     * @throws Exception Ошибка
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadUser(primaryStage);
    }

    /**
     * Загрузка пользователя
     *
     * @param stage Сцена
     * @throws IOException Ошибка загрузки пользователя
     */
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

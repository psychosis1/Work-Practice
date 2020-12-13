package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties.Current;
import properties.Properties;

import java.io.File;
import java.io.IOException;

public class Application {
    /**
     * Установка сцены
     *
     * @param object Обект
     * @param stage  Сцена
     * @param fxml   FXML
     * @param title  Заглавие
     * @throws IOException Ошибка установки сцены
     */
    public static void stage(Class<?> object, Stage stage, String fxml, String title) throws IOException {
        stage.close();
        Parent root = FXMLLoader.load(object.getResource(fxml));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(Properties.ICON);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Выход
     *
     * @param object Объект
     * @param stage  Сцена
     * @throws IOException Ошибка выхода
     */
    public static void logout(Class<?> object, Stage stage) throws IOException {
        setStageLogout(object, stage, "../fxml/login.fxml");
    }

    /**
     * Устновка сцены и удаление клиента
     * @param object Объект
     * @param stage Сцена
     * @param path Путь до файла
     * @throws IOException Ошибка выхода
     */
    private static void setStageLogout(Class<?> object, Stage stage, String path) throws IOException {
        Current.USER = null; //удаление текущего пользователя
        File file = new File("user.dat");
        if (file.delete())
            System.out.println("Файл успешно удален");
        else
            System.out.println("Не удалось удалить файл");
        stage(object, stage, path, "Авторизация");
    }

    /**
     * Выход с прописанным путем до файла
     *
     * @param object Объект
     * @param stage  Сцена
     * @param path   Путь до файла
     * @throws IOException Ошибка выхода
     */
    public static void logout(Class<?> object, Stage stage, String path) throws IOException {
        setStageLogout(object, stage, path);
    }
}

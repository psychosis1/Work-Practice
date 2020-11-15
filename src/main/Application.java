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

    public static void logout(Class<?> object, Stage stage) throws IOException {
        Current.USER = null; //удаление текущего пользователя
        File file = new File("user.dat");
        if (file.delete())
            System.out.println("Файл успешно удален");
        else
            System.out.println("Не удалось удалить файл");
        stage(object, stage, "../fxml/login.fxml", "Авторизация");
    }
}

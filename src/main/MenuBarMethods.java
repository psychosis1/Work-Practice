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

public class MenuBarMethods {
    void stage(MenuBar menu, String fxml, String title) throws IOException {
        Stage stage = (Stage) menu.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(Properties.ICON);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void logout(MenuBar menu) throws IOException {
        Current.USER = null; //удаление текущего пользователя
        File file = new File("user.dat");
        if(file.delete())
            System.out.println("Файл успешно удален");
        else
            System.out.println("Не удалось удалить файл");
        stage(menu, "../fxml/login.fxml", "Авторизация");
    }
}

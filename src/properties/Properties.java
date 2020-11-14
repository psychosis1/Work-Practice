package properties;

import javafx.scene.image.Image;

public class Properties {

    public static final Image ICON = new Image("/resources/logo.png");

    // Настройки для подключения к БД
    public static final String HOST = "localhost";
    public static final int PORT = 3306;
    public static final String DATABASE = "social_center";
    public static final String USER = "root";
    public static final String PASSWORD = "1234";

    private Properties() {}
}

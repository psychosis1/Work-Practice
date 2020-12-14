package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import properties.Properties;

public class Alerts {
    /**
     * Предупреждение
     *
     * @param header  Заголовок
     * @param content Контент
     */
    public static void warning(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        if (header.isEmpty()) alert.setHeaderText(null);
        else alert.setHeaderText(header);
        alert.setContentText(content);
        setIcon(alert);
        alert.show();
    }

    /**
     * Ошибка
     *
     * @param header  Заголовок
     * @param content Контент
     * @param height  Высота
     */
    public static void error(String header, String content, double height) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setHeight(height);
        setIcon(alert);
        alert.show();
    }

    /**
     * Ошибка
     *
     * @param header  Заголовок
     * @param content Контент
     */
    public static void error(String header, String content) {
        error(header, content, 0);
    }

    /**
     * Установка иконки
     *
     * @param dialog Диалоговое окно
     */
    public static void setIcon(Dialog<?> dialog) {
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(Properties.ICON); // иконка
    }

    /**
     * Информация
     *
     * @param title   Заглавие
     * @param header  Заголовок
     * @param content Контент
     * @param height  Высота
     */
    public static void info(String title, String header, String content, double height) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        if (height > 0) alert.setHeight(height);
        setIcon(alert);
        alert.show();
    }

    /**
     * Информация
     *
     * @param title   Заглавие
     * @param header  Заголовок
     * @param content Контент
     */
    public static void info(String title, String header, String content) {
        info(title, header, content, 0);
    }

    /**
     * Информация о нас
     */
    public static void aboutUs() {
        info("О нас", "АИС \"КЦСОН Красносельского района\"", "Автоматизированная информационная система, предоставляющая функциональные возможности " +
                "хранения, обработки и анализа клиентов социального центра." + "\n\n\n" +
                "Связь с разработчиками: " + "\n" +
                "Почта: social_center@social_center.com" + "\n" +
                "Телефон: +799999999", 600);
    }
}

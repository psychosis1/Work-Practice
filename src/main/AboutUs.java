package main;

import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class AboutUs {
    static void getText() {
        Alert result = new Alert(Alert.AlertType.INFORMATION);
        result.setTitle("О нас");
        result.setHeaderText("АИС \"КЦСОН Красносельского района\"");
        result.setContentText("Автоматизированная информационная система, предоставляющая функциональные возможности " +
                "хранения, обработки и анализа клиентов социального центра."+"\n\n\n"+
                "Связь с разработчиками: " + "\n" +
                "Почта: social_center@social_center.com" + "\n" +
                "Телефон: +799999999");
        result.setHeight(600);
        result.show();
    }
}

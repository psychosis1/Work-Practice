package choices;

import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class FieldControl {
    private String name;
    private String rusName;
    private ObservableList<String> choices;
    private Control control;

    public FieldControl(String name, String rusName, ObservableList<String> choices) {
        this.name = name;
        this.rusName = rusName;
        this.choices = choices;
    }

    /**
     * Получение имени поля
     *
     * @return имя поля
     */
    public String getName() {
        return name;
    }

    /**
     * Получение русского названия поля
     *
     * @return Русское название
     */
    public String getRusName() {
        return rusName;
    }

    /**
     * Получение выбора поля
     *
     * @return Выбор поля
     */
    public ObservableList<String> getChoices() {
        return choices;
    }

    /**
     * Получение конроллера
     *
     * @return Конроллер
     */
    public Control getControl() {
        return control;
    }

    /**
     * Установка конроллера
     *
     * @param control Конроллер
     */
    public void setControl(Control control) {
        this.control = control;
    }

    @Override
    public String toString() {
        return "FieldControl{" +
                "name='" + name + '\'' +
                ", rusName='" + rusName + '\'' +
                ", choices=" + choices +
                '}';
    }
}

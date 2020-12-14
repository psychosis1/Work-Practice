package choices;

import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

public class FieldControlGAGE {
    private String name;
    private String rusName;
    private Question choices;
    private Control control;
    private ToggleGroup toggleGroup;

    public FieldControlGAGE(String name, String rusName, Question choices) {
        this.name = name;
        this.rusName = rusName;
        this.choices = choices;
    }

    /**
     * Получение имени поля
     *
     * @return Имя поля
     */
    public String getName() {
        return name;
    }

    /**
     * Получение русского наименования поля
     *
     * @return Русское наименование поля
     */
    public String getRusName() {
        return rusName;
    }

    /**
     * Получение выбора поля
     *
     * @return Выбор поля
     */
    public Question getChoices() {
        return choices;
    }

    /**
     * Получение контроллера
     *
     * @return Контроллер
     */
    public Control getControl() {
        return control;
    }

    /**
     * Установка контроллера
     *
     * @param control Контроллер
     */
    public void setControl(Control control) {
        this.control = control;
    }

    /**
     * Получение группы RadioButton
     *
     * @return Группа RadioButton
     */
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    /**
     * Устновка группы RadioButton
     *
     * @param toggleGroup группа RadioButton
     */
    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}

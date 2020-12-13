package choices;

import javafx.scene.control.ToggleGroup;

import java.util.List;

public class FieldControlBoyko {
    private String name;
    private String rusName;
    private List<String> choices;
    private ToggleGroup toggleGroup;

    public FieldControlBoyko(String name, String rusName, List<String> choices) {
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
     * Устновка выбора поля
     *
     * @return Выбор поля
     */
    public List<String> getChoices() {
        return choices;
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
     * @param toggleGroup Группа RadioButton
     */
    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}

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

    public String getName() {
        return name;
    }

    public String getRusName() {
        return rusName;
    }

    public List<String> getChoices() {
        return choices;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}

package choices;

import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

public class FieldControlRadio {
    private String name;
    private String rusName;
    private TestGAGEQuestion choices;
    private Control control;
    private ToggleGroup toggleGroup;

    public FieldControlRadio(String name, String rusName, TestGAGEQuestion choices) {
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

    public TestGAGEQuestion getChoices() {
        return choices;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}

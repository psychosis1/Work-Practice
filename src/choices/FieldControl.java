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

    public String getName() {
        return name;
    }

    public String getRusName() {
        return rusName;
    }

    public ObservableList<String> getChoices() {
        return choices;
    }

    public Control getControl() {
        return control;
    }

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

package main;

import choices.ClientChoices;
import choices.FieldControl;
import database.ClientTable;
import implementation.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import properties.Current;
import properties.Properties;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;
import java.util.function.UnaryOperator;


public class ClientFormController {

    private final List<FieldControl> fields = new ArrayList<>();

    private final GridPane gridPane = new GridPane();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button save;

    @FXML
    public void initialize() {
        //установка полей
        setFields();

        scrollPane.setContent(gridPane);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        gridPane.getColumnConstraints().add(column);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        gridPane.getColumnConstraints().add(column2);

        int i = 0;
        for (FieldControl field : fields) {
            Label label = new Label(field.getRusName());
            if (field.getChoices() == null) {

                if (Arrays.asList("age", "number_children", "number_minor_children").contains(field.getName())) {
                    Spinner<Integer> spinnerInteger = new Spinner<>();
                    spinnerInteger.setEditable(true);
                    gridPane.addRow(i++, label, spinnerInteger);
                    viewRightColumn(spinnerInteger);
                    field.setControl(spinnerInteger);
                    formatSpinner(spinnerInteger); //запрет на ввод символов

                } else if (Arrays.asList("duration_of_use", "duration_of_alcohol", "duration_of_remission").contains(field.getName())) {
                    Spinner<Double> spinnerDouble = new Spinner<>(0.0, 100.0, 0.0, 0.5);
                    gridPane.addRow(i++, label, spinnerDouble);
                    viewRightColumn(spinnerDouble);
                    field.setControl(spinnerDouble);
                } else {
                    TextField textField = new TextField();
                    gridPane.addRow(i++, label, textField);
                    viewRightColumn(textField);
                    field.setControl(textField);
                }

            } else {
                ComboBox<String> comboBox = new ComboBox<>(field.getChoices());
                gridPane.addRow(i++, label, comboBox);
                viewRightColumn(comboBox);
                field.setControl(comboBox);
            }

            gridPane.getRowConstraints().add(new RowConstraints(40));
            GridPane.setMargin(label, new Insets(20));
        }

        if (Current.CLIENT != null)
            clientNotNull(); //если клиент существует
    }

    private void viewRightColumn(Control control) {
        GridPane.setMargin(control, new Insets(20));
        //растягивание по горизонтали
        control.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(control, Priority.ALWAYS);
    }

    private void formatSpinner(Spinner<Integer> spinnerInteger) {
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0 ||
                        parsePosition.getIndex() < c.getControlNewText().length()) {
                    // reject parsing the complete text failed
                    return null;
                }
            }
            return c;
        };
        TextFormatter<Integer> priceFormatter = new TextFormatter<Integer>(
                new IntegerStringConverter(), 0, filter);

        spinnerInteger.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 100, Integer.parseInt("0")));
        spinnerInteger.getEditor().setTextFormatter(priceFormatter);
    }

    private void setFields() {
        LinkedHashMap<String, ObservableList<String>> rusChoice = new LinkedHashMap<>();
        rusChoice.put("Код", null);
        rusChoice.put("ФИО клиента", null);
        rusChoice.put("Пол", ClientChoices.SEX);
        rusChoice.put("Возраст", null);
        rusChoice.put("Образование", ClientChoices.EDUCATION);
        rusChoice.put("Место работы", ClientChoices.WORK_PLACE);
        rusChoice.put("Семейное положение", ClientChoices.MARITAL_STATUS);
        rusChoice.put("Особый социальный статус", ClientChoices.SPECIAL_SOCIAL_STATUS);
        rusChoice.put("Другой особый социальный статус", null);
        rusChoice.put("Наличие документов", ClientChoices.YESNO);
        rusChoice.put("Регистрация", ClientChoices.REGISTRATION);
        rusChoice.put("ВИЧ-статус", ClientChoices.YESNO);
        rusChoice.put("Состоит ли на учёте в центре СПИД", ClientChoices.YESNO);
        rusChoice.put("Получает ли лечение по ВИЧ", ClientChoices.YESNO);
        rusChoice.put("Гепатит С", ClientChoices.YESNO);
        rusChoice.put("Инвалидность", ClientChoices.DISABILITY_GROUP);
        rusChoice.put("Употреблял ли наркотики за последний месяц", ClientChoices.YESNO);
        rusChoice.put("Длительность употребления наркотиков (в годах)", null);
        rusChoice.put("Употребляемые вещества", null);
        rusChoice.put("Состоит ли на учёте в наркологическом диспансере", ClientChoices.YESNO);
        rusChoice.put("Употреблял ли алкоголь за последний месяц", ClientChoices.YESNO);
        rusChoice.put("Длительность употребления алкоголя (в годах)", null);
        rusChoice.put("Проходил ли лечение/реабилитацию от алкоголизма/наркомании", ClientChoices.YESNO);
        rusChoice.put("Длительность ремиссии (в годах)", null);
        rusChoice.put("Состоит ли на учёте в ОДН РУВД", ClientChoices.YESNO);
        rusChoice.put("Рассматривалось ли дело на КДН и ЗП", ClientChoices.YESNO);
        rusChoice.put("Подвергался ли физическому (сексуальному) насилию", ClientChoices.YESNO);
        rusChoice.put("Количество детей", null);
        rusChoice.put("Количество несовершеннолетних детей", null);
        rusChoice.put("Место нахождения детей", ClientChoices.LOCATION);
        rusChoice.put("Вид жилого помещения", ClientChoices.TYPE);
        rusChoice.put("Уровень дохода клиента", ClientChoices.LEVEL);
        rusChoice.put("Обеспеченность клиента и его семьи полноценным питанием, одеждой, обувью, предметами личной гигиены", ClientChoices.SECURITY);

        List<String> names = Client.getFieldsNames();
        names.remove(0); //удаление из списка имен idClient
        int i = 0;
        for (Map.Entry<String, ObservableList<String>> item : rusChoice.entrySet()) {
            fields.add(new FieldControl(names.get(i), item.getKey(), item.getValue()));
            i++;
        }

    }

    private void clientNotNull() {
        for (FieldControl field : fields) {
            if (field.getChoices() == null) {
                if (Arrays.asList("age", "number_children", "number_minor_children").contains(field.getName())) {
                    Spinner<Integer> spinnerInteger = (Spinner<Integer>) field.getControl();
                    spinnerInteger.getValueFactory().setValue((int) Current.CLIENT.getField(field.getName()));
                } else if (Arrays.asList("duration_of_use", "duration_of_alcohol", "duration_of_remission").contains(field.getName())) {
                    Spinner<Double> spinnerDouble = (Spinner<Double>) field.getControl();
                    spinnerDouble.getValueFactory().setValue((double) Current.CLIENT.getField(field.getName()));
                } else {
                    TextField textField = (TextField) field.getControl();
                    textField.setText((String) Current.CLIENT.getField(field.getName()));
                }
            } else {
                ComboBox<String> comboBox = (ComboBox<String>) field.getControl();
                comboBox.getSelectionModel().select((int) Current.CLIENT.getField(field.getName()));
            }
        }
        save.setText("Изменить");
    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
        if (save.getText().equals("Добавить")) {
            Client client = new Client();
            if (saveInClient(client).insert(client) == 0)
                save.setText("Изменить");
        } else {
            saveInClient(Current.CLIENT).update();
        }
    }

    private ClientTable saveInClient(Client client) {
        for (FieldControl field : fields) {
            if (field.getChoices() == null) {
                if (Arrays.asList("age", "number_children", "number_minor_children").contains(field.getName())) {
                    Spinner<Integer> spinnerInteger = (Spinner<Integer>) field.getControl();
                    client.setField(field.getName(), spinnerInteger.getValue());
                } else if (Arrays.asList("duration_of_use", "duration_of_alcohol", "duration_of_remission").contains(field.getName())) {
                    Spinner<Double> spinnerDouble = (Spinner<Double>) field.getControl();
                    client.setField(field.getName(), spinnerDouble.getValue());
                } else {
                    TextField textField = (TextField) field.getControl();
                    client.setField(field.getName(), textField.getText());
                }
            } else {
                ComboBox<String> comboBox = (ComboBox<String>) field.getControl();
                client.setField(field.getName(), comboBox.getItems().indexOf(comboBox.getValue()));
            }
        }

        System.out.println(client);

        return new ClientTable();
    }

    @FXML
    private void delete(ActionEvent actionEvent) throws IOException {
        if (new ClientTable().delete() == 0) {
            Application.stage(getClass(), (Stage) save.getScene().getWindow(), "../fxml/general.fxml", "Главная страница");
        }
    }
}

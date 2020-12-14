package main.client;

import choices.*;
import database.TestBoykoTable;
import entity.TestBoyko;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import main.Alerts;
import main.client.interpretation.InterTestBoyko;
import properties.Current;

import java.util.*;

public class TestBoykoController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button save;

    @FXML
    private Label title;

    @FXML
    private Button result;

    private final VBox vBox = new VBox();

    private final List<FieldControlBoyko> fields = new ArrayList<>();

    private TestBoyko testBoyko = new TestBoyko();

    private final InterTestBoyko inter = new InterTestBoyko();

    private final TestBoykoTable table = new TestBoykoTable();

    /**
     * Инициализация окна
     */
    @FXML
    public void initialize() {
        //установка попытки теста
        askAttempt();

        //установка полей
        setFields();

        scrollPane.setContent(vBox);

        vBox.setFillWidth(true);
        vBox.setSpacing(10);

        setNodes();

        testBoykoNotNull();
    }

    /**
     * Установка контроллеров
     */
    private void setNodes() {
        for (FieldControlBoyko field : fields) {

            TextFlow label = new TextFlow();
            Text text = new Text(field.getRusName());
            text.setStyle("-fx-font-weight: bold");
            label.getChildren().add(text);
            VBox.setMargin(label, new Insets(0, 10, 0, 10));
            label.setTextAlignment(TextAlignment.CENTER); //тект по центру
            vBox.getChildren().add(label);

            List<RadioButton> radioButtons = new ArrayList<>();
            ToggleGroup toggleGroup = new ToggleGroup();
            for (String choice : field.getChoices()) {
                RadioButton radioButton = new RadioButton(choice);
                radioButton.setToggleGroup(toggleGroup);
                radioButtons.add(radioButton);
                VBox.setMargin(radioButton, new Insets(0, 20, 0, 20));
            }
            field.setToggleGroup(toggleGroup);
            vBox.getChildren().addAll(radioButtons);

        }
    }

    /**
     * Тест Бойко не пустой
     */
    private void testBoykoNotNull() {
        if (table.selectTestBoyko(testBoyko) > 0) {
            for (FieldControlBoyko field : fields) {
                if ((int) testBoyko.getField(field.getName()) != -1) {
                    ToggleGroup toggleGroup = field.getToggleGroup();
                    ObservableList<RadioButton> radioButtons = (ObservableList) toggleGroup.getToggles();
                    String find = field.getChoices().get((int) testBoyko.getField(field.getName()));
                    for (RadioButton radioButton : radioButtons)
                        if (radioButton.getText().equals(find))
                            radioButton.setSelected(true);

                }
            }
            save.setText("Изменить");
            result.setVisible(true);
            inter.select(testBoyko); //получение результата
        } else {
            save.setText("Добавить"); //значение кнопки по умолчанию
            result.setVisible(false);
        }
    }

    /**
     * Выбор диагностики
     */
    private void askAttempt() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Первичная", "Вторичная");
        dialog.setTitle("Диагностика");
        dialog.setHeaderText(null);
        dialog.setContentText("Диагностика:");

        Alerts.setIcon(dialog);

        Optional<String> result = dialog.showAndWait();


        result.ifPresentOrElse(
                (attempt)
                        -> {
                    if (attempt.equals("Первичная")) {
                        testBoyko.setAttempt(1);
                        title.setText("Тест Бойко (Первичная диагностика)");
                    } else {
                        testBoyko.setAttempt(2);
                        title.setText("Тест Бойко (Вторичная диагностика)");
                    }
                },
                ()
                        -> {
                    testBoyko.setAttempt(1);
                    title.setText("Тест Бойко (Первичная диагностика)");
                });

        testBoyko.setClient(Current.CLIENT.getIdClient()); //установка клиента
    }

    /**
     * Установка полей
     */
    private void setFields() {
        LinkedHashMap<String, List<String>> rusChoice = new LinkedHashMap<>();
        rusChoice.put("1. Агрессивность/аутоагрессивность", TestBoykoChoices.AGGRESS);
        rusChoice.put("2. Тревога и депрессия", TestBoykoChoices.ALARM);
        rusChoice.put("3. Нарушения памяти, мышления, истощаемост", TestBoykoChoices.MEMORY_DISORDER);
        rusChoice.put("4.Способность критически воспринимать свое состояние и поведение", TestBoykoChoices.CRITICISM);
        rusChoice.put("5. Способности к самообслуживанию", TestBoykoChoices.SELF_SERVICE);
        rusChoice.put("6. Трудовая (профессиональная) деятельность", TestBoykoChoices.WORK_ACTIVITY);
        rusChoice.put("7. Контакты с друзьями, знакомыми", TestBoykoChoices.FRIENDS);
        rusChoice.put("8. Семейные отношения", TestBoykoChoices.FAMILY_RELATION);
        rusChoice.put("9. Забота о детях/родителях", TestBoykoChoices.CHILD_PARENT);
        rusChoice.put("10. Сфера досуга", TestBoykoChoices.LEISURE);

        List<String> names = TestBoyko.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestBoyko
        names.remove(0); //удаление из списка имен client
        int i = 0;
        for (Map.Entry<String, List<String>> item : rusChoice.entrySet()) {
            fields.add(new FieldControlBoyko(names.get(i), item.getKey(), item.getValue()));
            i++;
        }

    }

    /**
     * Добавление или изменение теста Бойко
     *
     * @param actionEvent Событие
     */
    @FXML
    private void saveChanges(ActionEvent actionEvent) {
        if (save.getText().equals("Добавить")) {
            if (saveInTestBoyko()) {
                int number = table.insert(testBoyko);
                if (number > -1) {
                    testBoyko.setIdTestBoyko(number);
                    save.setText("Изменить");
                    result.setVisible(true);
                    inter.insert(testBoyko);
                }
            }
        } else {
            if (saveInTestBoyko()) {
                table.update(testBoyko);
                inter.update(testBoyko);
            }
        }
    }

    /**
     * Сохранение теста Бойко
     *
     * @return Булевое значение
     */
    private boolean saveInTestBoyko() {
        //Все вопросы обязательны
        for (FieldControlBoyko check : fields) {
            if (check.getToggleGroup().getSelectedToggle() == null) {
                Alerts.warning("Невозможно создать тест", "Все вопросы должны быть заполнены. Они обязательны.");
                return false;
            }
        }

        for (FieldControlBoyko field : fields) {
            ToggleGroup toggleGroup = field.getToggleGroup();
            RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
            if (selected != null)
                testBoyko.setField(field.getName(), field.getChoices().indexOf(selected.getText()));
            else
                testBoyko.setField(field.getName(), -1);
        }

        System.out.println(testBoyko);
        return true;
    }

    /**
     * Удаление теста Бойко
     *
     * @param actionEvent Событие
     */
    @FXML
    private void delete(ActionEvent actionEvent) {
        if (testBoyko.getIdTestBoyko() > 0) {
            if (table.delete(testBoyko) == 0) {
                testBoyko = new TestBoyko();
                vBox.getChildren().clear();
                again();
            }
        } else Alerts.warning("Невозможно произвести удаление", "Тест не был создан, чтобы его удалить.");
    }

    /**
     * После удаления
     */
    private void again() {
        //установка попытки теста
        askAttempt();

        setNodes();
        testBoykoNotNull();
    }

    /**
     * Получение интерпретации
     *
     * @param actionEvent Событие
     */
    @FXML
    private void getResult(ActionEvent actionEvent) {
        saveChanges(actionEvent);
        inter.getResult();
    }


}

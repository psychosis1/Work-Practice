package main;

import choices.FieldControl;
import choices.FieldControlRadio;
import choices.TestGAGEChoices;
import choices.TestGAGEQuestion;
import database.TestGAGETable;
import entity.TestGAGE;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import properties.Current;

import java.util.*;

public class TestGAGEController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button save;

    private final VBox vBox = new VBox();

    private final List<FieldControlRadio> fields = new ArrayList<>();

    private TestGAGE testGAGE = new TestGAGE();

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

        testGAGENotNull();
    }

    private void setNodes() {
        for (FieldControlRadio field : fields) {
            TextFlow label = new TextFlow();
            Text text = new Text(field.getRusName());
            text.setStyle("-fx-font-weight: bold");
            label.getChildren().add(text);
            VBox.setMargin(label, new Insets(0, 10, 0, 10));
            vBox.getChildren().add(label);

            if (field.getChoices() == null) {
                TextField textField = new TextField();
                field.setControl(textField);
                vBox.getChildren().add(textField);
            } else {
                List<RadioButton> radioButtons = new ArrayList<>();
                ToggleGroup toggleGroup = new ToggleGroup();
                for (String choice : field.getChoices().getNames()) {
                    RadioButton radioButton = new RadioButton(choice);
                    radioButton.setToggleGroup(toggleGroup);
                    radioButtons.add(radioButton);
                    VBox.setMargin(radioButton, new Insets(0, 20, 0, 20));
                }
                field.setToggleGroup(toggleGroup);
                vBox.getChildren().addAll(radioButtons);
            }
        }
    }

    private void testGAGENotNull() {
        if (new TestGAGETable().selectTestGAGE(testGAGE) > 0) {
            for (FieldControlRadio field : fields) {
                if (field.getChoices() == null) {
                    TextField textField = (TextField) field.getControl();
                    textField.setText((String) testGAGE.getField(field.getName()));
                } else {
                    if ((int) testGAGE.getField(field.getName()) != -1) {
                        ToggleGroup toggleGroup = field.getToggleGroup();
                        ObservableList<RadioButton> radioButtons = (ObservableList) toggleGroup.getToggles();
                        for (RadioButton radioButton : radioButtons)
                            if (radioButton.getText().equals(field.getChoices().getName((int) testGAGE.getField(field.getName()))))
                                radioButton.setSelected(true);

                    }

                }
            }
            save.setText("Изменить");
        }
    }

    private void askAttempt() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Первичная", "Вторичная");
        dialog.setTitle("Диагностика");
        dialog.setHeaderText(null);
        dialog.setContentText("Диагностика:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(attempt -> {
            if (attempt.equals("Первичная")) testGAGE.setAttempt(1);
            else testGAGE.setAttempt(2);
        });

        if (testGAGE.getAttempt() == 0) testGAGE.setAttempt(1);
        testGAGE.setClient(Current.CLIENT.getIdClient());
    }

    private void setFields() {
        LinkedHashMap<String, TestGAGEQuestion> rusChoice = new LinkedHashMap<>();
        rusChoice.put("1. Злоупотребляли ли Вы алкоголем?", TestGAGEChoices.NOYES);
        rusChoice.put("2. Пробовали ли Вы какие-нибудь вещества, изменяющие настроение и состояние, кроме алкоголя?", TestGAGEChoices.SUBSTANCES);
        rusChoice.put("3.1. Бывали ли в Вашей жизни ситуации, когда вы теряли документы и(или) деньги, когда выпьете или употребите другие вещества, меняющие состояние?", TestGAGEChoices.NOYES);
        rusChoice.put("3.1.1. Когда последний раз?", TestGAGEChoices.LOSS_DOCUMENTS_WHEN);
        rusChoice.put("3.1.2. Было ли такое один раз в вашей жизни?", TestGAGEChoices.LOSS_DOCUMENTS_TIME);
        rusChoice.put("3.2. Бывали ли в Вашей жизни ситуации, когда Вы не могли выйти на работу из-за похмелья или состояния опьянения?", TestGAGEChoices.NOYES);
        rusChoice.put("3.2.1. Когда последний раз?", TestGAGEChoices.DO_NOT_WORK_WHEN);
        rusChoice.put("3.3. Теряли ли Вы отношения с друзьями или любовные, потому что Ваш друг или партнер были недовольны вашими отношениями с алкоголем?", TestGAGEChoices.NOYES);
        rusChoice.put("3.4. Бывало ли, что Вы вступали в драку или Вас били, когда Вы были в состоянии алкогольного опьянения?", TestGAGEChoices.NOYES);
        rusChoice.put("3.5. Получали ли Вы травмы в состоянии алкогольного опьянения?", TestGAGEChoices.INJURIES);
        rusChoice.put("3.6. Бывало ли, что Вы выпивали больше полбутылки водки, или бутылки вина или полутора литров пива за один раз (за один вечер)?", TestGAGEChoices.NOYES);
        rusChoice.put("3.6.1. Как часто такое случается?", TestGAGEChoices.LOTS_ALCOHOL_TIME);
        rusChoice.put("3.7. Как часто Вы пьете алкогольные напитки, хотя бы чуть-чуть?", TestGAGEChoices.DRINK_ALCOHOL_TIME);
        rusChoice.put("3.8. Какие вещества, влияющие на настроение и изменяющие сознание, кроме алкоголя Вы пробовали?", null);
        rusChoice.put("Какое количество наименований?", TestGAGEChoices.TRY_SUBSTANCES);
        rusChoice.put("3.9. Каким способом Вы употребляли наркотические или токсические вещества (назвать то, что клиент отвечал на вопрос 3.8.)?", TestGAGEChoices.HOW_USE);
        rusChoice.put("3.10. Бывали ли трудности на работе или в личных отношениях, когда Вы были под действием веществ или  потому, что другие люди были слишком обеспокоены Вашей увлеченностью наркотическим веществом?", TestGAGEChoices.NOYES);
        rusChoice.put("3.11. Бывало ли, что Вы себя плохо чувствовали после употребления наркотического вещества (в момент опьянения или после выхода) (сильное сердцебиение, слабость, головная боль, тошнота, дискомфорт в ЖКТ)?", TestGAGEChoices.POOR_HEALTH);
        rusChoice.put("3.12. употребляли ли Вы вещества, меняющие настроение, один или в компании?", TestGAGEChoices.COMPANY);
        rusChoice.put("4.1. Вы думали когда-нибудь о том, чтобы уменьшить  количество употребляемого алкоголя (наркотических веществ)?", TestGAGEChoices.NOYES);
        rusChoice.put("4.2. Вас раздражает, если люди критикуют Вас за употребление алкоголя (употребление наркотиков)?", TestGAGEChoices.NOYES);
        rusChoice.put("4.3. Вы испытывали когда-нибудь чувство вины по поводу того, что слишком много или долго употребляли алкоголь (наркотики)?", TestGAGEChoices.NOYES);
        rusChoice.put("4.4. Вы когда-нибудь употребляли алкоголь (наркотические вещества) для поднятия тонуса утром или с похмелья?", TestGAGEChoices.NOYES);

        List<String> names = TestGAGE.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestGAGE
        names.remove(0); //удаление из списка имен client
        int i = 0;
        for (Map.Entry<String, TestGAGEQuestion> item : rusChoice.entrySet()) {
            fields.add(new FieldControlRadio(names.get(i), item.getKey(), item.getValue()));
            i++;
        }

    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
        if (save.getText().equals("Добавить")) {
            int number = Objects.requireNonNull(saveInTestGAGE()).insert(testGAGE);
            if (number > -1) {
                testGAGE.setIdTestGAGE(number);
                save.setText("Изменить");
            }
        } else {
            Objects.requireNonNull(saveInTestGAGE()).update(testGAGE);
        }
    }

    private TestGAGETable saveInTestGAGE() {
        //Первые два вопроса обязательны
        if (fields.get(0).getToggleGroup().getSelectedToggle() != null && fields.get(1).getToggleGroup().getSelectedToggle() != null) {

            for (FieldControlRadio field : fields) {
                if (field.getChoices() == null) {
                    TextField textField = (TextField) field.getControl();
                    testGAGE.setField(field.getName(), textField.getText());
                } else {
                    ToggleGroup toggleGroup = field.getToggleGroup();
                    RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
                    if (selected != null)
                        testGAGE.setField(field.getName(), field.getChoices().getValue(selected.getText()));
                    else
                        testGAGE.setField(field.getName(), -1);
                }
            }

            System.out.println(testGAGE);
            return new TestGAGETable();

        }
        showAlert();
        return null;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Невозможно создать тест");
        alert.setContentText("Нет ответа на 1 и 2 вопрос. Они обязательны.");
        alert.show();
    }

    @FXML
    private void delete(ActionEvent actionEvent) {
        if (new TestGAGETable().delete(testGAGE) == 0) {
            testGAGE = new TestGAGE();
            vBox.getChildren().clear();
            again();

        }
    }

    private void again() {
        //установка попытки теста
        askAttempt();

        setNodes();
        save.setText("Добавить");
    }
}

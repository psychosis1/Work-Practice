package main.interpretation;

import choices.InterTestBoykoChoices;
import database.InterpretationTestBoykoTable;
import entity.InterpretationTestBoyko;
import entity.TestBoyko;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import properties.Properties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class InterTestBoyko extends Inter<TestBoyko> {
    private final InterpretationTestBoyko inter = new InterpretationTestBoyko();

    @Override
    public void calculate(TestBoyko test) {
        testAndDate(test.getIdTestBoyko()); //установка id теста и текущего времени

        List<String> list = TestBoyko.getFieldsNames();
        list.remove(0); //удаление idTestBoyko
        list.remove(0); //удаление client
        list.remove(list.size() - 1); //удаление attempt

        int sum = 0;
        for (String name : list) {
            int value = (int) test.getField(name);
            inter.setField(name, value);
            sum += value;
        }

        inter.setOverall_points(sum);

        if (sum < 11)
            inter.setOverall_assessment(0);
        else if (sum < 21)
            inter.setOverall_assessment(1);
        else if (sum < 31)
            inter.setOverall_assessment(2);
        else if (sum < 41)
            inter.setOverall_assessment(3);

    }

    @Override
    public void testAndDate(int id) {
        inter.setTest(id);
        inter.setDate(LocalDateTime.now());
    }

    @Override
    public void getResult() {
        Alert result = new Alert(Alert.AlertType.INFORMATION);
        result.setTitle("Результат");
        result.setHeaderText(null);
        TextFlow flow = new TextFlow();
        flow.setLineSpacing(10);
        flow.setPrefWidth(500);

        List<String> results = Arrays.asList(
                "Агрессивность/аутоагрессивность: ",
                "Тревога и депрессия: ",
                "Нарушения памяти, мышления, истощаемость: ",
                "Способность критически воспринимать свое состояние и поведение: ",
                "Способности к самообслуживанию: ",
                "Трудовая (профессиональная) деятельность: ",
                "Контакты с друзьями, знакомыми: ",
                "Семейные отношения: ",
                "Забота о детях/родителях: ",
                "Сфера досуга: ",
                "Общая оценка социального функционирования: ",
                "Дата прохождения теста: "
        );
        List<String> answers = Arrays.asList(
                InterTestBoykoChoices.AGGRESS.get(inter.getAggressiveness()),
                InterTestBoykoChoices.ALARM.get(inter.getAlarm()),
                InterTestBoykoChoices.MEMORY_DISORDER.get(inter.getMemory_disorder()),
                InterTestBoykoChoices.CRITICISM.get(inter.getCriticism()),
                InterTestBoykoChoices.SELF_SERVICE.get(inter.getSelf_service()),
                InterTestBoykoChoices.WORK_ACTIVITY.get(inter.getWork_activity()),
                InterTestBoykoChoices.FRIENDS.get(inter.getFriends()),
                InterTestBoykoChoices.FAMILY_RELATION.get(inter.getFamily_relation()),
                InterTestBoykoChoices.CHILD_PARENT.get(inter.getChild_parent()),
                InterTestBoykoChoices.LEISURE.get(inter.getLeisure()),
                InterTestBoykoChoices.OVERALL_ASSESSMENT.get(inter.getOverall_assessment()),
                inter.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );

        for (int i = 0; i < results.size(); i++) {
            Text text = new Text(results.get(i));
            text.setStyle("-fx-font-weight: bold");
            flow.getChildren().addAll(text, new Text(answers.get(i) + "\n"));
        }

        Stage stage = (Stage) result.getDialogPane().getScene().getWindow();
        stage.getIcons().add(Properties.ICON); // иконка

        result.getDialogPane().setContent(flow);
        result.show();
    }

    @Override
    public void insert(TestBoyko test) {
        calculate(test);
        int code = new InterpretationTestBoykoTable().insert(inter);
        if (code != -1) inter.setIdInterpretationBoyko(code);
    }

    @Override
    public void update(TestBoyko test) {
        calculate(test);
        new InterpretationTestBoykoTable().update(inter);
    }

    @Override
    public void select(TestBoyko test) {
        inter.setTest(test.getIdTestBoyko());
        new InterpretationTestBoykoTable().select(inter);
    }
}

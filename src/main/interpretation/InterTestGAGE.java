package main.interpretation;

import database.InterpretationTestGAGETable;
import entity.InterpretationTestGAGE;
import entity.TestGAGE;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import properties.Properties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InterTestGAGE {
    private InterpretationTestGAGE iterTestGAGE = new InterpretationTestGAGE();

    public void calculate(TestGAGE testGAGE) {
        testAndDate(testGAGE.getIdTestGAGE()); //установка id теста и текущего времени

        int sum1 = 0, sum2 = 0;
        List<String> list = TestGAGE.getFieldsNames();
        list.remove(0); //удаление idTestGAGE
        list.remove(0); //удаление client
        list.remove(list.size() - 1); //удаление attempt

        for (int i = 2; i < 21; i++) { //overall_points_risk_abuse
            int buf = integerValue(testGAGE.getField(list.get(i)).toString());
            if (buf != -1) {
                sum1 += buf;
            }
        }

        for (int i = 21; i < list.size(); i++) { //overall_points_risk_dependency
            int buf = integerValue(testGAGE.getField(list.get(i)).toString());
            if (buf != -1) {
                sum2 += buf;
            }
        }

        iterTestGAGE.setOverall_points_risk_abuse(sum1);
        iterTestGAGE.setOverall_points_risk_dependency(sum2);

        setText();
    }

    public void testAndDate(int id) {
        iterTestGAGE.setTest(id);
        iterTestGAGE.setDate(LocalDateTime.now());
    }

    public void setText() {
        if (iterTestGAGE.getOverall_points_risk_abuse() >= 3)
            iterTestGAGE.setRisk_abuse("выраженные признаки злоупотребления");
        else iterTestGAGE.setRisk_abuse("нет выраженных признаков злоупотребления");

        if (iterTestGAGE.getOverall_points_risk_dependency() >= 2)
            iterTestGAGE.setRisk_dependency("имеются признаки зависимости");
        else iterTestGAGE.setRisk_dependency("нет выраженных признаков зависимости");
    }


    public int integerValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void getResult() {
        Alert result = new Alert(Alert.AlertType.INFORMATION);
        result.setTitle("Результат");
        result.setHeaderText(null);
        Text text = new Text("\n"
                + "Риск злоупотребления: " + iterTestGAGE.getRisk_abuse() + "\n"
                + "Риск зависимости: " + iterTestGAGE.getRisk_dependency() + "\n"
                + "Дата прохождения теста: " + iterTestGAGE.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        text.setWrappingWidth(400);
        text.setLineSpacing(10);

        Stage stage = (Stage) result.getDialogPane().getScene().getWindow();
        stage.getIcons().add(Properties.ICON); // иконка

        result.getDialogPane().setContent(text);
        result.show();
    }

    public void insert(TestGAGE testGAGE) {
        calculate(testGAGE);
        int code = new InterpretationTestGAGETable().insert(iterTestGAGE);
        if (code != -1) iterTestGAGE.setIdInterpretationGAGE(code);
    }

    public void update(TestGAGE testGAGE) {
        calculate(testGAGE);
        new InterpretationTestGAGETable().update(iterTestGAGE);
    }

    public void select(TestGAGE testGAGE) {
        iterTestGAGE.setTest(testGAGE.getIdTestGAGE());
        new InterpretationTestGAGETable().select(iterTestGAGE);
    }
}

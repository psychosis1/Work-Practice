package choices;

import java.util.List;

public class Question {
    private final List<String> names;
    private List<Integer> values;

    public Question(List<String> name, List<Integer> value) {
        this.names = name;
        this.values = value;
    }

    /**
     * Получение списка вариантов ответов
     *
     * @return Список вариантво ответа
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * Получение балла по выборанному ответу
     *
     * @param name Выбранный ответ
     * @return Балл
     */
    public int getValue(String name) {
        int index = names.indexOf(name);
        return values.get(index);
    }

    /**
     * Получение ответа по баллу
     *
     * @param value Балл
     * @return Ответ
     */
    public String getName(int value) {
        int index = values.indexOf(value);
        return names.get(index);
    }

    @Override
    public String toString() {
        return "TestGAGEQuestion{" +
                "names=" + names +
                ", values=" + values +
                '}';
    }
}

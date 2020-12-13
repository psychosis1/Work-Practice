package main.client.interpretation;

public abstract class Inter<T> {
    /**
     * Подсчет баллов
     *
     * @param test Тест
     */
    public abstract void calculate(T test);

    /**
     * Устновка id теста и текущего времени
     *
     * @param id id теста
     */
    public abstract void testAndDate(int id);

    /**
     * Проверка на численное значение
     *
     * @param value Значение
     * @return Код операции
     */
    public int integerValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Получение результата интерпретации
     */
    public abstract void getResult();

    /**
     * Вставка интерпретации
     *
     * @param test Тест
     */
    public abstract void insert(T test);

    /**
     * Обновление интерпретации
     *
     * @param test Тест
     */
    public abstract void update(T test);

    /**
     * Выборка интерпретации
     *
     * @param test Тест
     */
    public abstract void select(T test);
}

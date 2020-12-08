package main.interpretation;

public abstract class Inter<T> {
    public abstract void calculate(T test);
    public abstract void testAndDate(int id);

    public int integerValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public abstract void getResult();

    public abstract void insert(T test);

    public abstract void update(T test);

    public abstract void select(T test);
}

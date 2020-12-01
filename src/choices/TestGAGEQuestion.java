package choices;

import java.util.List;

public class TestGAGEQuestion {
    private final List<String> names;
    private List<Integer> values;

    public TestGAGEQuestion(List<String> name, List<Integer> value) {
        this.names = name;
        this.values = value;
    }

    public List<String> getNames() {
        return names;
    }

    public List<Integer> getValues() {
        return values;
    }

    public int getValue(String name) {
        int index = names.indexOf(name);
        return values.get(index);
    }

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

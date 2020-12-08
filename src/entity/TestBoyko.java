package entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBoyko {
    private int idTestBoyko;
    private int client;
    private int aggressiveness;
    private int alarm;
    private int memory_disorder;
    private int criticism;
    private int self_service;
    private int work_activity;
    private int friends;
    private int family_relation;
    private int child_parent;
    private int leisure;
    private int attempt;

    public int getIdTestBoyko() {
        return idTestBoyko;
    }

    public void setIdTestBoyko(int idTestBoyko) {
        this.idTestBoyko = idTestBoyko;
    }

    public int getClient(){
        return client;
    }

    public void setClient(int client){
        this.client=client;
    }


    public int getAttempt(){
        return attempt;
    }

    public void setAttempt(int attempt){
        this.attempt=attempt;
    }

    /**
     * Получение названий полей
     */
    static public List<String> getFieldsNames() {
        Field[] fields = TestBoyko.class.getDeclaredFields();
        List<String> names = new ArrayList<>();
        Arrays.asList(fields).forEach(field -> names.add(field.getName()));
        return names;
    }

    /**
     * Установка значения поля
     * @param name название поля
     * @param value значение
     */
    public void setField(String name, Object value) {
        try {
            Field field = this.getClass().getDeclaredField(name);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            System.out.println("Нет такого поля");
        }

    }

    /**
     * Получение поля
     * @param name название поля
     * @return значение
     */
    public Object getField(String name) {
        try {
            Field field = this.getClass().getDeclaredField(name);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            System.out.println("Нет такого поля");
        }
        return null;
    }

    @Override
    public String toString() {
        return "TestBoyko{" +
                "idTestBoyko=" + idTestBoyko +
                ", client=" + client +
                ", aggressiveness=" + aggressiveness +
                ", alarm=" + alarm +
                ", memory_disorder=" + memory_disorder +
                ", criticism=" + criticism +
                ", self_service=" + self_service +
                ", work_activity=" + work_activity +
                ", friends=" + friends +
                ", family_relation=" + family_relation +
                ", child_parent=" + child_parent +
                ", leisure=" + leisure +
                ", attempt=" + attempt +
                '}';
    }
}

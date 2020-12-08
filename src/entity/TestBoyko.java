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

    public int getAggressiveness() {
        return aggressiveness;
    }

    public void setAggressiveness(int aggressiveness) {
        this.aggressiveness = aggressiveness;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getMemory_disorder() {
        return memory_disorder;
    }

    public void setMemory_disorder(int memory_disorder) {
        this.memory_disorder = memory_disorder;
    }

    public int getCriticism() {
        return criticism;
    }

    public void setCriticism(int criticism) {
        this.criticism = criticism;
    }

    public int getSelf_service() {
        return self_service;
    }

    public void setSelf_service(int self_service) {
        this.self_service = self_service;
    }

    public int getWork_activity() {
        return work_activity;
    }

    public void setWork_activity(int work_activity) {
        this.work_activity = work_activity;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getFamily_relation() {
        return family_relation;
    }

    public void setFamily_relation(int family_relation) {
        this.family_relation = family_relation;
    }

    public int getChild_parent() {
        return child_parent;
    }

    public void setChild_parent(int child_parent) {
        this.child_parent = child_parent;
    }

    public int getLeisure() {
        return leisure;
    }

    public void setLeisure(int leisure) {
        this.leisure = leisure;
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

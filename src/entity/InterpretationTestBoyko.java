package entity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterpretationTestBoyko {
    private int idInterpretationBoyko;
    private int test;
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
    private int overall_points;
    private int overall_assessment;
    private LocalDateTime date;

    public int getIdInterpretationBoyko() {
        return idInterpretationBoyko;
    }

    public void setIdInterpretationBoyko(int idInterpretationBoyko) {
        this.idInterpretationBoyko = idInterpretationBoyko;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
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

    public int getOverall_points() {
        return overall_points;
    }

    public void setOverall_points(int overall_points) {
        this.overall_points = overall_points;
    }

    public int getOverall_assessment() {
        return overall_assessment;
    }

    public void setOverall_assessment(int overall_assessment) {
        this.overall_assessment = overall_assessment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Получение названий полей
     */
    static public List<String> getFieldsNames() {
        Field[] fields = InterpretationTestBoyko.class.getDeclaredFields();
        List<String> names = new ArrayList<>();
        Arrays.asList(fields).forEach(field -> names.add(field.getName()));
        return names;
    }

    /**
     * Установка значения поля
     *
     * @param name  название поля
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
     *
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
}

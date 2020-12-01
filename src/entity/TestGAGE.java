package entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGAGE {
    private int idTestGAGE;
    private int client;
    private int alcohol;
    private int substances;
    private int loss_documents;
    private int loss_documents_when;
    private int loss_documents_time;
    private int do_not_work;
    private int do_not_work_when;
    private int loss_loved_ones;
    private int fight;
    private int injuries;
    private int lots_alcohol;
    private int lots_alcohol_time;
    private int drink_alcohol_time;
    private String try_substances;
    private int try_substances_choice;
    private int how_use;
    private int difficulties;
    private int poor_health;
    private int company;
    private int dose_reduction;
    private int irritation;
    private int fault;
    private int tone;
    private int attempt;

    public TestGAGE() {}

    public TestGAGE(int idTestGAGE, int client, int alcohol, int substances, int loss_documents, int loss_documents_when, int loss_documents_time, int do_not_work, int do_not_work_when, int loss_loved_ones, int fight, int injuries, int lots_alcohol, int lots_alcohol_time, int drink_alcohol_time, String try_substances, int try_substances_choice, int how_use, int difficulties, int poor_health, int company, int dose_reduction, int irritation, int fault, int tone, int attempt) {
        this.idTestGAGE = idTestGAGE;
        this.client = client;
        this.alcohol = alcohol;
        this.substances = substances;
        this.loss_documents = loss_documents;
        this.loss_documents_when = loss_documents_when;
        this.loss_documents_time = loss_documents_time;
        this.do_not_work = do_not_work;
        this.do_not_work_when = do_not_work_when;
        this.loss_loved_ones = loss_loved_ones;
        this.fight = fight;
        this.injuries = injuries;
        this.lots_alcohol = lots_alcohol;
        this.lots_alcohol_time = lots_alcohol_time;
        this.drink_alcohol_time = drink_alcohol_time;
        this.try_substances = try_substances;
        this.try_substances_choice = try_substances_choice;
        this.how_use = how_use;
        this.difficulties = difficulties;
        this.poor_health = poor_health;
        this.company = company;
        this.dose_reduction = dose_reduction;
        this.irritation = irritation;
        this.fault = fault;
        this.tone = tone;
        this.attempt = attempt;
    }

    public int getIdTestGAGE() {
        return idTestGAGE;
    }

    public void setIdTestGAGE(int idTestGAGE) {
        this.idTestGAGE = idTestGAGE;
    }

    public int getClient(){
        return client;
    }

    public void setClient(int client){
        this.client=client;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    /**
     * Получение названий полей
     */
    static public List<String> getFieldsNames() {
        Field[] fields = TestGAGE.class.getDeclaredFields();
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
        return "TestGAGE{" +
                "idTestGAGE=" + idTestGAGE +
                ", client=" + client +
                ", alcohol=" + alcohol +
                ", substances=" + substances +
                ", loss_documents=" + loss_documents +
                ", loss_documents_when=" + loss_documents_when +
                ", loss_documents_time=" + loss_documents_time +
                ", do_not_work=" + do_not_work +
                ", do_not_work_when=" + do_not_work_when +
                ", loss_loved_ones=" + loss_loved_ones +
                ", fight=" + fight +
                ", injuries=" + injuries +
                ", lots_alcohol=" + lots_alcohol +
                ", lots_alcohol_time=" + lots_alcohol_time +
                ", drink_alcohol_time=" + drink_alcohol_time +
                ", try_substances='" + try_substances + '\'' +
                ", try_substances_choice=" + try_substances_choice +
                ", how_use=" + how_use +
                ", difficulties=" + difficulties +
                ", poor_health=" + poor_health +
                ", company=" + company +
                ", dose_reduction=" + dose_reduction +
                ", irritation=" + irritation +
                ", fault=" + fault +
                ", tone=" + tone +
                ", attempt=" + attempt +
                '}';
    }
}

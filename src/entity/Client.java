package entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    private int idClient;
    private String code;
    private String full_name;
    private int sex;
    private int age;
    private int education;
    private int work_place;
    private int marital_status;
    private int special_social_status;
    private String another_special_social_status;
    private int av_doc;
    private int registration;
    private int HIV_status;
    private int AIDS_center;
    private int HIV_get_treatment;
    private int hepatitis_c;
    private int disability_group;
    private int use_last_month;
    private double duration_of_use;
    private String substances_used;
    private int accounting_in_narcological_clinic;
    private int alcohol_last_month;
    private double duration_of_alcohol;
    private int the_treatment_was;
    private double duration_of_remission;
    private int accounting_in_ODN_RUVD;
    private int case_examined_in_KDN_ZP;
    private int experience_sexual_physical_abuse;
    private int number_children;
    private int number_minor_children;
    private int location;
    private int type_room;
    private int income_level;
    private int client_security;

    public Client() {
    }

    public Client(int idClient, String code, String full_name, int sex, int age, int education, int work_place, int marital_status, int special_social_status, String another_special_social_status, int av_doc, int registration, int HIV_status, int AIDS_center, int HIV_get_treatment, int hepatitis_c, int disability_group, int use_last_month, double duration_of_use, String substances_used, int accounting_in_narcological_clinic, int alcohol_last_month, double duration_of_alcohol, int the_treatment_was, double duration_of_remission, int accounting_in_ODN_RUVD, int case_examined_in_KDN_ZP, int experience_sexual_physical_abuse, int number_children, int number_minor_children, int location, int type_room, int income_level, int client_security) {
        this.idClient = idClient;
        this.code = code;
        this.full_name = full_name;
        this.sex = sex;
        this.age = age;
        this.education = education;
        this.work_place = work_place;
        this.marital_status = marital_status;
        this.special_social_status = special_social_status;
        this.another_special_social_status = another_special_social_status;
        this.av_doc = av_doc;
        this.registration = registration;
        this.HIV_status = HIV_status;
        this.AIDS_center = AIDS_center;
        this.HIV_get_treatment = HIV_get_treatment;
        this.hepatitis_c = hepatitis_c;
        this.disability_group = disability_group;
        this.use_last_month = use_last_month;
        this.duration_of_use = duration_of_use;
        this.substances_used = substances_used;
        this.accounting_in_narcological_clinic = accounting_in_narcological_clinic;
        this.alcohol_last_month = alcohol_last_month;
        this.duration_of_alcohol = duration_of_alcohol;
        this.the_treatment_was = the_treatment_was;
        this.duration_of_remission = duration_of_remission;
        this.accounting_in_ODN_RUVD = accounting_in_ODN_RUVD;
        this.case_examined_in_KDN_ZP = case_examined_in_KDN_ZP;
        this.experience_sexual_physical_abuse = experience_sexual_physical_abuse;
        this.number_children = number_children;
        this.number_minor_children = number_minor_children;
        this.location = location;
        this.type_room = type_room;
        this.income_level = income_level;
        this.client_security = client_security;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getWork_place() {
        return work_place;
    }

    public void setWork_place(int work_place) {
        this.work_place = work_place;
    }

    public int getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(int marital_status) {
        this.marital_status = marital_status;
    }

    public int getSpecial_social_status() {
        return special_social_status;
    }

    public void setSpecial_social_status(int special_social_status) {
        this.special_social_status = special_social_status;
    }

    public String getAnother_special_social_status() {
        return another_special_social_status;
    }

    public void setAnother_special_social_status(String another_special_social_status) {
        this.another_special_social_status = another_special_social_status;
    }

    public int getAv_doc() {
        return av_doc;
    }

    public void setAv_doc(int av_doc) {
        this.av_doc = av_doc;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public int getHiv_status() {
        return HIV_status;
    }

    public void setHiv_status(int HIV_status) {
        this.HIV_status = HIV_status;
    }

    public int getAids_center() {
        return AIDS_center;
    }

    public void setAids_center(int AIDS_center) {
        this.AIDS_center = AIDS_center;
    }

    public int getHiv_get_treatment() {
        return HIV_get_treatment;
    }

    public void setHiv_get_treatment(int HIV_get_treatment) {
        this.HIV_get_treatment = HIV_get_treatment;
    }

    public int getHepatitis_c() {
        return hepatitis_c;
    }

    public void setHepatitis_c(int hepatitis_c) {
        this.hepatitis_c = hepatitis_c;
    }

    public int getDisability_group() {
        return disability_group;
    }

    public void setDisability_group(int disability_group) {
        this.disability_group = disability_group;
    }

    public int getUse_last_month() {
        return use_last_month;
    }

    public void setUse_last_month(int use_last_month) {
        this.use_last_month = use_last_month;
    }

    public double getDuration_of_use() {
        return duration_of_use;
    }

    public void setDuration_of_use(double duration_of_use) {
        this.duration_of_use = duration_of_use;
    }

    public String getSubstances_used() {
        return substances_used;
    }

    public void setSubstances_used(String substances_used) {
        this.substances_used = substances_used;
    }

    public int getAccounting_in_narcological_clinic() {
        return accounting_in_narcological_clinic;
    }

    public void setAccounting_in_narcological_clinic(int accounting_in_narcological_clinic) {
        this.accounting_in_narcological_clinic = accounting_in_narcological_clinic;
    }

    public int getAlcohol_last_month() {
        return alcohol_last_month;
    }

    public void setAlcohol_last_month(int alcohol_last_month) {
        this.alcohol_last_month = alcohol_last_month;
    }

    public double getDuration_of_alcohol() {
        return duration_of_alcohol;
    }

    public void setDuration_of_alcohol(double duration_of_alcohol) {
        this.duration_of_alcohol = duration_of_alcohol;
    }

    public int getThe_treatment_was() {
        return the_treatment_was;
    }

    public void setThe_treatment_was(int the_treatment_was) {
        this.the_treatment_was = the_treatment_was;
    }

    public double getDuration_of_remission() {
        return duration_of_remission;
    }

    public void setDuration_of_remission(double duration_of_remission) {
        this.duration_of_remission = duration_of_remission;
    }

    public int getAccounting_in_odn_ruvd() {
        return accounting_in_ODN_RUVD;
    }

    public void setAccounting_in_odn_ruvd(int accounting_in_ODN_RUVD) {
        this.accounting_in_ODN_RUVD = accounting_in_ODN_RUVD;
    }

    public int getCase_examined_in_kdn_zp() {
        return case_examined_in_KDN_ZP;
    }

    public void setCase_examined_in_kdn_zp(int case_examined_in_KDN_ZP) {
        this.case_examined_in_KDN_ZP = case_examined_in_KDN_ZP;
    }

    public int getExperience_sexual_physical_abuse() {
        return experience_sexual_physical_abuse;
    }

    public void setExperience_sexual_physical_abuse(int experience_sexual_physical_abuse) {
        this.experience_sexual_physical_abuse = experience_sexual_physical_abuse;
    }

    public int getNumber_children() {
        return number_children;
    }

    public void setNumber_children(int number_children) {
        this.number_children = number_children;
    }

    public int getNumber_minor_children() {
        return number_minor_children;
    }

    public void setNumber_minor_children(int number_minor_children) {
        this.number_minor_children = number_minor_children;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getType_room() {
        return type_room;
    }

    public void setType_room(int type_room) {
        this.type_room = type_room;
    }

    public int getIncome_level() {
        return income_level;
    }

    public void setIncome_level(int income_level) {
        this.income_level = income_level;
    }

    public int getClient_security() {
        return client_security;
    }

    public void setClient_security(int client_security) {
        this.client_security = client_security;
    }

    /**
     * Получение названий полей
     */
    static public List<String> getFieldsNames() {
        Field[] fields = Client.class.getDeclaredFields();
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
        return "Client{" +
                "idClient=" + idClient +
                ", code='" + code + '\'' +
                ", full_name='" + full_name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", education=" + education +
                ", work_place=" + work_place +
                ", marital_status=" + marital_status +
                ", special_social_status=" + special_social_status +
                ", another_special_social_status='" + another_special_social_status + '\'' +
                ", av_doc=" + av_doc +
                ", registration=" + registration +
                ", HIV_status=" + HIV_status +
                ", AIDS_center=" + AIDS_center +
                ", HIV_get_treatment=" + HIV_get_treatment +
                ", hepatitis_c=" + hepatitis_c +
                ", disability_group=" + disability_group +
                ", use_last_month=" + use_last_month +
                ", duration_of_use=" + duration_of_use +
                ", substances_used='" + substances_used + '\'' +
                ", accounting_in_narcological_clinic=" + accounting_in_narcological_clinic +
                ", alcohol_last_month=" + alcohol_last_month +
                ", duration_of_alcohol=" + duration_of_alcohol +
                ", the_treatment_was=" + the_treatment_was +
                ", duration_of_remission=" + duration_of_remission +
                ", accounting_in_ODN_RUVD=" + accounting_in_ODN_RUVD +
                ", case_examined_in_KDN_ZP=" + case_examined_in_KDN_ZP +
                ", experience_sexual_physical_abuse=" + experience_sexual_physical_abuse +
                ", number_children=" + number_children +
                ", number_minor_children=" + number_minor_children +
                ", location=" + location +
                ", type_room=" + type_room +
                ", income_level=" + income_level +
                ", client_security=" + client_security +
                '}';
    }
}

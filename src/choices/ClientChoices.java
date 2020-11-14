package choices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientChoices {
    public static final ObservableList<String> YESNO = FXCollections.observableArrayList("нет", "да");
    public static final ObservableList<String> SEX = FXCollections.observableArrayList("мужской", "женский");
    public static final ObservableList<String> EDUCATION = FXCollections.observableArrayList("без образования", "начальная школа", "неоконченное среднее", "9 классов", "11 классов", "среднее специальное", "неоконченное высшее", "высшее");
    public static final ObservableList<String> WORK_PLACE = FXCollections.observableArrayList("постоянное", "временное", "эпизодическое", "не работает");
    public static final ObservableList<String> MARITAL_STATUS = FXCollections.observableArrayList("нет постоянных отношений", "вдовец/вдова", "разведён/разведена", "гражданский брак", "официальный брак");
    public static final ObservableList<String> SPECIAL_SOCIAL_STATUS = FXCollections.observableArrayList("нет", "многодетная семья", "одинокий родитель", "лицо из числа детей-сирот и детей, оставшихся без попечения родителей", "другой");
    public static final ObservableList<String> REGISTRATION = FXCollections.observableArrayList("постоянная", "временная");
    public static final ObservableList<String> DISABILITY_GROUP = FXCollections.observableArrayList("нет", "3-я группа", "2-я группа", "1-я группа");
    public static final ObservableList<String> LOCATION = FXCollections.observableArrayList("в семье, живёт вместе и поддерживает отношения", "отдельно, но поддерживает отношения", "отдельно, не поддерживает отношения", "в доме ребёнка");
    public static final ObservableList<String> TYPE = FXCollections.observableArrayList("отсутствует", "съёмное", "комната с родственниками в коммунальной квартире", "собственная комната в коммунальной квартире", "собственная комната в квартире с родственниками", "комната в общежитии", "собственная квартира", "собственный дом");
    public static final ObservableList<String> LEVEL = FXCollections.observableArrayList("нет дохода", "ниже прожиточного минимума", "на уровне прожиточного минимума", "выше прожиточного минимума");
    public static final ObservableList<String> SECURITY = FXCollections.observableArrayList("недостаточная", "удовлетворительная", "достаточная");
}

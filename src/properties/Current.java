package properties;

import entity.Client;
import entity.User;

public class Current {
    public static User USER = null;
    public static Client CLIENT = null;
    public static User REFACTOR_USER = null;

    /**
     * Запрет на создание конструктора
     */
    private Current() {
    }
}

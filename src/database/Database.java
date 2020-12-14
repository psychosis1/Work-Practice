package database;

import com.mysql.cj.jdbc.MysqlDataSource;
import properties.Properties;

import java.sql.*;

public class Database {

    private MysqlDataSource source;

    /**
     * Подключение к БД
     *
     * @return Связь с БД
     * @throws SQLException Ошибка подключения
     */
    public Connection connect() throws SQLException {
        if (source == null) {
            source = new MysqlDataSource();
            source.setServerName(Properties.HOST);
            source.setPort(Properties.PORT);
            source.setDatabaseName(Properties.DATABASE);
            source.setUser(Properties.USER);
            source.setPassword(Properties.PASSWORD);

            source.setServerTimezone("Europe/Moscow");
        }
        return source.getConnection();
    }
}

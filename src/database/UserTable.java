package database;

import entity.Client;
import entity.InterpretationTestGAGE;
import entity.User;
import properties.Current;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTable extends Database {
    private static final Logger log = Logger.getLogger(UserTable.class.getName());

    /**
     * Выбор пользователя из базы данных по логину
     * @param username - логин
     * @param password - пароль
     * @return
     */
    public int selectUser(String username, String password) {

        String sql = "SELECT * FROM User WHERE username = ?";

        try (Connection conn = this.connect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    Current.USER = new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("patronymic"),
                            rs.getString("position"),
                            rs.getBoolean("admin")
                    );
                    return 0;
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    /**
     * Получение списка всех пользователй из бады данный
     * @return лист со всеми пользователями
     */
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                userList.add(new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("patronymic"),
                        rs.getString("position"),
                        rs.getBoolean("admin")
                ));
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return userList;
    }

    /**
     * Добавление пользователя в БД
     * @param username - логин
     * @param password - пароль
     * @param first_name - имя
     * @param last_name - фамилия
     * @param patronymic - отчество
     * @param position - должность
     * @param admin - является ли админом
     * @return - true/false
     */
    public boolean createNew(String username,
                             String password,
                             String first_name,
                             String last_name,
                             String patronymic,
                             String position,
                             boolean admin) {
        String sql = "INSERT INTO User (username,password,first_name,last_name,patronymic,position,admin) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, username);
            pstmt.setObject(2, password);
            pstmt.setObject(3, first_name);
            pstmt.setObject(4, last_name);
            pstmt.setObject(5, patronymic);
            pstmt.setObject(6, position);
            pstmt.setObject(7, admin);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return false;
        }


    }

    /**
     * удаление пользователя из БД
     * @param username - логин
     * @return - true/false
     */
    public boolean deleteUser(String username){
        String sql = "DELETE FROM User WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, username);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return false;
        }
    }

    /**
     * изменение значения поля в базе данных с пользователями
     * @param username - логин
     * @param field - название поля
     * @param value - значение поля
     * @return - true/false
     */
    public boolean updateField(String username,String field, String value){
        String sql = "UPDATE User SET " + field + " = ? WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, value);
            pstmt.setObject(2, username);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return false;
        }
    }

    /**
     * изменение значения поля в базе данных с пользователями
     * @param field - название поля
     * @param value - значение
     * @return
     */
    public int update(String field, String value) {
        //создание sql запроса
        String sql = "UPDATE User SET " + field + " = ? WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, value);
            pstmt.setObject(2, Current.USER.getUsername());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return 0;
    }


}

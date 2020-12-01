package database;

import entity.User;
import properties.Current;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTable extends Database {
    private static final Logger log = Logger.getLogger(UserTable.class.getName());

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
                            rs.getString("position"));
                    return 0;
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }
}

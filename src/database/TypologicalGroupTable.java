package database;

import entity.TypologicalGroup;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypologicalGroupTable extends Database {
    private static final Logger log = Logger.getLogger(TypologicalGroup.class.getName());

    public int insert(TypologicalGroup group) {
        //создание sql запроса
        String sql = "INSERT INTO TypologicalGroup (test_boyko, test_gage, client, `group`, subgroup)" +
                " VALUES (?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setObject(1, group.getTest_boyko());
            pstmt.setObject(2, group.getTest_gage());
            pstmt.setObject(3, group.getClient());
            pstmt.setObject(4, group.getGroup());
            pstmt.setObject(5, group.getSubgroup());

            pstmt.executeUpdate();

            //получение сгенерированного id
            ResultSet key = pstmt.getGeneratedKeys();
            if (key.next())
                return key.getInt(1);

        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return -1;
    }

    public void update(TypologicalGroup group) {
        //создание sql запроса
        String sql = "UPDATE TypologicalGroup SET test_boyko = ?, test_gage = ?, `group` = ?, subgroup = ? WHERE idTypologicalGroup = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, group.getTest_boyko());
            pstmt.setObject(2, group.getTest_gage());
            pstmt.setObject(3, group.getGroup());
            pstmt.setObject(4, group.getSubgroup());
            pstmt.setObject(5, group.getIdTypologicalGroup());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    public boolean select(TypologicalGroup group,int attempt) {
        String sql = "select * from typologicalgroup where test_gage = (select idTestGAGE from testgage where testgage.client=? && attempt=?) && test_boyko = (select idTestBoyko from testboyko where testboyko.client = ? && attempt=?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, group.getClient());
            pstmt.setInt(2, attempt);
            pstmt.setInt(3, group.getClient());
            pstmt.setInt(4, attempt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                group.setIdTypologicalGroup(rs.getInt(1));
                group.setTest_boyko(rs.getInt(2));
                group.setTest_gage(rs.getInt(3));
                group.setClient(rs.getInt(4));
                group.setGroup(rs.getInt(5));
                group.setSubgroup(rs.getInt(6));
                return true;
            }


        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return false;
    }

}

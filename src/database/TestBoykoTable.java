package database;

import entity.TestBoyko;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBoykoTable extends Database{
    private static final Logger log = Logger.getLogger(TestGAGETable.class.getName());

    public int insert(TestBoyko testBoyko) {
        List<String> names = TestBoyko.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestBoyko

        //создание sql запроса
        StringBuilder sql = new StringBuilder("INSERT INTO TestBoyko(");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append(names.get(i)).append(",");
        }
        sql.append(names.get(names.size() - 1));
        sql.append(") VALUES(");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append("?").append(",");
        }
        sql.append("?").append(")");

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql), PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            for (String name : names) {
                pstmt.setObject(i, testBoyko.getField(name));
                i++;
            }
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

    public void update(TestBoyko testBoyko) {
        List<String> names = TestBoyko.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestBoyko
        names.remove(0); //удаление из списка имен client

        //создание sql запроса
        StringBuilder sql = new StringBuilder("UPDATE TestBoyko SET ");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append(names.get(i)).append(" = ?").append(",");
        }
        sql.append(names.get(names.size() - 1)).append(" = ?");
        sql.append(" WHERE idTestBoyko = ?");

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
            int i = 1;
            for (String name : names) {
                pstmt.setObject(i, testBoyko.getField(name));
                i++;
            }
            pstmt.setInt(i, testBoyko.getIdTestBoyko()); //установка id

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    public int delete(TestBoyko testBoyko) {
        //создание sql запроса
        String sql = "DELETE FROM TestBoyko WHERE idTestBoyko = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testBoyko.getIdTestBoyko()); //установка id
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return 0;
    }

    public int selectTestBoyko(TestBoyko testBoyko) {
        String sql = "SELECT * FROM TestBoyko WHERE client = ? AND attempt = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testBoyko.getClient());
            pstmt.setInt(2, testBoyko.getAttempt());
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) //пустая выборка
                return 0;
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i=1;i<=rsmd.getColumnCount();i++) {
                testBoyko.setField(rsmd.getColumnName(i),rs.getObject(i));
            }

        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return testBoyko.getIdTestBoyko();
    }
}

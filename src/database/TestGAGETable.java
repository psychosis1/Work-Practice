package database;

import entity.Client;
import entity.TestGAGE;
import properties.Current;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestGAGETable extends Database {
    private static final Logger log = Logger.getLogger(TestGAGETable.class.getName());

    public int insert(TestGAGE testGAGE) {
        List<String> names = TestGAGE.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestGAGE

        //создание sql запроса
        StringBuilder sql = new StringBuilder("INSERT INTO TestGAGE(");
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
                pstmt.setObject(i, testGAGE.getField(name));
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

    public void update(TestGAGE testGAGE) {
        List<String> names = TestGAGE.getFieldsNames();
        names.remove(0); //удаление из списка имен idTestGAGE
        names.remove(0); //удаление из списка имен client

        //создание sql запроса
        StringBuilder sql = new StringBuilder("UPDATE TestGAGE SET ");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append(names.get(i)).append(" = ?").append(",");
        }
        sql.append(names.get(names.size() - 1)).append(" = ?");
        sql.append(" WHERE idTestGAGE = ?");

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
            int i = 1;
            for (String name : names) {
                pstmt.setObject(i, testGAGE.getField(name));
                i++;
            }
            pstmt.setInt(i, testGAGE.getIdTestGAGE()); //установка id

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    public int delete(TestGAGE testGAGE) {
        //создание sql запроса
        String sql = "DELETE FROM TestGAGE WHERE idTestGAGE = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testGAGE.getIdTestGAGE()); //установка id
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return 0;
    }

    public int selectTestGAGE(TestGAGE testGAGE) {
        String sql = "SELECT * FROM TestGAGE WHERE client = ? AND attempt = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testGAGE.getClient());
            pstmt.setInt(2, testGAGE.getAttempt());
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) //пустая выборка
                return 0;
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i=1;i<=rsmd.getColumnCount();i++) {
                testGAGE.setField(rsmd.getColumnName(i),rs.getObject(i));
            }

        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return testGAGE.getIdTestGAGE();
    }
}

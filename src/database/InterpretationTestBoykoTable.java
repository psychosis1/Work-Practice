package database;

import entity.InterpretationTestBoyko;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterpretationTestBoykoTable extends Database {
    private static final Logger log = Logger.getLogger(InterpretationTestBoykoTable.class.getName());

    /**
     * Вставка интерпретации Бойко
     *
     * @param inter Интерпретация Бойко
     * @return Код операции
     */
    public int insert(InterpretationTestBoyko inter) {
        List<String> names = InterpretationTestBoyko.getFieldsNames();
        names.remove(0); //удаление из списка имен id

        //создание sql запроса
        StringBuilder sql = new StringBuilder("INSERT INTO InterpretationBoyko(");
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
                pstmt.setObject(i, inter.getField(name));
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

    /**
     * Обновление интерпретации Бойко
     *
     * @param inter Интерпретация Бойко
     */
    public void update(InterpretationTestBoyko inter) {
        List<String> names = InterpretationTestBoyko.getFieldsNames();
        names.remove(0); //удаление из списка имен id
        names.remove(0); //удаление из списка имен test

        //создание sql запроса
        StringBuilder sql = new StringBuilder("UPDATE InterpretationBoyko SET ");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append(names.get(i)).append(" = ?").append(",");
        }
        sql.append(names.get(names.size() - 1)).append(" = ?");
        sql.append(" WHERE idInterpretationBoyko = ?");

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
            int i = 1;
            for (String name : names) {
                pstmt.setObject(i, inter.getField(name));
                i++;
            }
            pstmt.setInt(i, inter.getIdInterpretationBoyko()); //установка id

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Выбор интерпретации Бойко
     *
     * @param inter Интерпретация Бойко
     */
    public void select(InterpretationTestBoyko inter) {
        String sql = "SELECT * FROM InterpretationBoyko WHERE test = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inter.getTest());

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next())
                return;

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount() - 1; i++) {
                inter.setField(rsmd.getColumnName(i), rs.getObject(i));
            }
            inter.setDate(rs.getTimestamp(rsmd.getColumnCount()).toLocalDateTime());


        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }
}

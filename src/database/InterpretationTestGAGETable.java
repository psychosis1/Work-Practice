package database;

import entity.InterpretationTestGAGE;
import entity.TestGAGE;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterpretationTestGAGETable extends Database {
    private static final Logger log = Logger.getLogger(InterpretationTestGAGETable.class.getName());

    public int insert(InterpretationTestGAGE interpretationTestGAGE) {
        //создание sql запроса
        String sql = "INSERT INTO InterpretationGAGE (test,overall_points_risk_abuse,risk_abuse,overall_points_risk_dependency,risk_dependency,date)" +
                " VALUES (?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setObject(1, interpretationTestGAGE.getTest());
            pstmt.setObject(2, interpretationTestGAGE.getOverall_points_risk_abuse());
            pstmt.setObject(3, interpretationTestGAGE.getRisk_abuse());
            pstmt.setObject(4, interpretationTestGAGE.getOverall_points_risk_dependency());
            pstmt.setObject(5, interpretationTestGAGE.getRisk_dependency());
            pstmt.setObject(6, interpretationTestGAGE.getDate());

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

    public void update(InterpretationTestGAGE interpretationTestGAGE) {
        //создание sql запроса
        String sql = "UPDATE InterpretationGAGE SET overall_points_risk_abuse = ?, risk_abuse = ?, overall_points_risk_dependency = ?," +
                " risk_dependency = ?,date = ? WHERE idInterpretationGAGE = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, interpretationTestGAGE.getOverall_points_risk_abuse());
            pstmt.setObject(2, interpretationTestGAGE.getRisk_abuse());
            pstmt.setObject(3, interpretationTestGAGE.getOverall_points_risk_dependency());
            pstmt.setObject(4, interpretationTestGAGE.getRisk_dependency());
            pstmt.setObject(5, interpretationTestGAGE.getDate());
            pstmt.setObject(6, interpretationTestGAGE.getIdInterpretationGAGE());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    public void select(InterpretationTestGAGE interpretationTestGAGE) {
        String sql = "SELECT * FROM InterpretationGAGE WHERE test = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, interpretationTestGAGE.getTest());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                interpretationTestGAGE.setIdInterpretationGAGE(rs.getInt(1));
                interpretationTestGAGE.setTest(rs.getInt(2));
                interpretationTestGAGE.setOverall_points_risk_abuse(rs.getInt(3));
                interpretationTestGAGE.setRisk_abuse(rs.getString(4));
                interpretationTestGAGE.setOverall_points_risk_dependency(rs.getInt(5));
                interpretationTestGAGE.setRisk_dependency(rs.getString(6));
                interpretationTestGAGE.setDate(rs.getTimestamp(7).toLocalDateTime());
            }


        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }
}

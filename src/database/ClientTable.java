package database;

import entity.Client;
import properties.Current;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTable extends Database {
    private static final Logger log = Logger.getLogger(ClientTable.class.getName());

    public List<Client> selectClientsForGeneral() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("idClient"),
                        rs.getString("code"),
                        rs.getString("full_name"),
                        rs.getInt("sex"),
                        rs.getInt("age"),
                        rs.getInt("education"),
                        rs.getInt("work_place"),
                        rs.getInt("marital_status"),
                        rs.getInt("special_social_status"),
                        rs.getString("another_special_social_status"),
                        rs.getInt("av_doc"),
                        rs.getInt("registration"),
                        rs.getInt("HIV_status"),
                        rs.getInt("AIDS_center"),
                        rs.getInt("HIV_get_treatment"),
                        rs.getInt("hepatitis_c"),
                        rs.getInt("disability_group"),
                        rs.getInt("use_last_month"),
                        rs.getDouble("duration_of_use"),
                        rs.getString("substances_used"),
                        rs.getInt("accounting_in_narcological_clinic"),
                        rs.getInt("alcohol_last_month"),
                        rs.getDouble("duration_of_alcohol"),
                        rs.getInt("the_treatment_was"),
                        rs.getDouble("duration_of_remission"),
                        rs.getInt("accounting_in_ODN_RUVD"),
                        rs.getInt("case_examined_in_KDN_ZP"),
                        rs.getInt("experience_sexual_physical_abuse"),
                        rs.getInt("number_children"),
                        rs.getInt("number_minor_children"),
                        rs.getInt("location"),
                        rs.getInt("type_room"),
                        rs.getInt("income_level"),
                        rs.getInt("client_security")
                ));
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return clients;
    }

    public int insert(Client client) {
        List<String> names = Client.getFieldsNames();
        names.remove(0); //удаление из списка имен idClient

        //создание sql запроса
        StringBuilder sql = new StringBuilder("INSERT INTO Client(");
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
                pstmt.setObject(i, client.getField(name));
                i++;
            }
            pstmt.executeUpdate();

            //получение сгенерированного id для нового клиента
            ResultSet key = pstmt.getGeneratedKeys();
            if (key.next())
                client.setIdClient(key.getInt(1));

            Current.CLIENT = client; //установка текущего клиента
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return 0;
    }

    public void update() {
        List<String> names = Client.getFieldsNames();
        names.remove(0); //удаление из списка имен idClient

        //создание sql запроса
        StringBuilder sql = new StringBuilder("UPDATE Client SET ");
        for (int i = 0; i < names.size() - 1; i++) {
            sql.append(names.get(i)).append(" = ?").append(",");
        }
        sql.append(names.get(names.size() - 1)).append(" = ?");
        sql.append(" WHERE idClient = ?");

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
            int i = 1;
            for (String name : names) {
                pstmt.setObject(i, Current.CLIENT.getField(name));
                i++;
            }
            pstmt.setInt(i, Current.CLIENT.getIdClient()); //установка id

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    public int delete() {
        //создание sql запроса
        String sql = "DELETE FROM Client WHERE idClient = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Current.CLIENT.getIdClient()); //установка id
            pstmt.executeUpdate();

            Current.CLIENT = null; //удаление текущего клиента
        } catch (SQLException e) {
            log.log(Level.WARNING, e.getMessage());
            return -1;
        }
        return 0;
    }
}

package by.bsu.melnik.hospital;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by ilyah on 10.12.2016.
 */
public class Main {


    public static void main (String[] args) {

        DBWorker worker = new DBWorker();
        String query = "SELECT * FROM USER";
        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                User user = new User();
                user.setIduser(resultSet.getInt("iduser"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPatronimyc(resultSet.getString("patronymic"));
                user.setStatus(resultSet.getInt("status"));
                user.setDiagnosis(resultSet.getString("diagnosis"));

                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

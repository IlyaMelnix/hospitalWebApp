package by.bsu.melnik.hospital;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by ilyah on 10.12.2016.
 */
public class Main {


    private static final String URL = "jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main (String[] args) {

        try {

            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {

            System.err.println("Не удалось загрузить класс драйвера!");
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
        ){
            if (!connection.isClosed()){

                System.out.println("Соединение с БД установлено!");
            }

            //statement.execute("INSERT INTO `hospital`.`drug` (`drugName`, `drugDesc`, `drugDosing`, `user_iduser`) VALUES ('ЛИНЕКС ', 'пор внутр 1.5г N10 (Лек, Словения)', 'По 1 пак. 1 раз в день', '3');");
            ResultSet res = statement.executeQuery("SELECT * FROM hospital.drug");
            connection.close();
            if (connection.isClosed()){

                System.out.println("Соединение с БД закрыто!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

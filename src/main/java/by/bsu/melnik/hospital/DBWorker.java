package by.bsu.melnik.hospital;

import java.sql.*;


public class DBWorker {


    private static final String URL = "jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBWorker() {
        try
//             (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement();)
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()){

                System.out.println("Соединение с БД установлено!");
            }

            //statement.execute("INSERT INTO `hospital`.`drug` (`drugName`, `drugDesc`, `drugDosing`, `user_iduser`) VALUES ('ЛИНЕКС ', 'пор внутр 1.5г N10 (Лек, Словения)', 'По 1 пак. 1 раз в день', '3');");
            //ResultSet res = statement.executeQuery("SELECT * FROM hospital.drug");

//            connection.close();
//            if (connection.isClosed()){
//
//                System.out.println("Соединение с БД закрыто!");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

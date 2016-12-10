package by.bsu.melnik.hospital;

import java.sql.*;


public class DBWorker {


    private static final String URL = "jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBWorker() {
        try
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()){

                System.out.println("Соединение с БД установлено!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

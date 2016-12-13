package by.bsu.melnik.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
* Использует Singleton.
 */

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static ConnectionPool instance;

    private BlockingQueue<Connection> queue;

    private ConnectionPool() {
        this.queue = new ArrayBlockingQueue<>(5);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 5; i++){

                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                this.queue.put(connection);
            }
        } catch (ClassNotFoundException | InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return queue.poll();
    }

    public void releaseConnection(Connection connection){
        queue.offer(connection);
    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }
}

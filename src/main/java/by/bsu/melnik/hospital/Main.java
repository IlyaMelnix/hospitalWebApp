package by.bsu.melnik.hospital;

import by.bsu.melnik.hospital.model.User;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;


public class Main {

    private static final String INSERT_NEW_USER = "INSERT INTO `hospital`.`user` (`username`, `password`, `name`, `surname`, `patronymic`, `status`, `diagnosis`) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM `hospital`.`user` WHERE `iduser`= ? ;";
    public static void main (String[] args) {


        String query = "SELECT * FROM USER";

        PreparedStatement preparedStatement = null;


        try {
            DBWorker worker = new DBWorker();

            int addedUserID = 100;
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            // Добавление пользователя
            preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW_USER);

            preparedStatement.setString(1,"testuser");
            preparedStatement.setString(2,"testuser");
            preparedStatement.setString(3,"Имя");
            preparedStatement.setString(4,"Фамилия");
            preparedStatement.setString(5,"Отчество");
            preparedStatement.setInt(6,1);
            preparedStatement.setString(7,"Диагноз");

            preparedStatement.execute();
            System.out.println("Пользователь успешно добавлен!");

            // Получаем всех пользователей
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
//                User user = new User();
//                user.setIduser(resultSet.getInt("iduser"));
//                addedUserID = resultSet.getInt("iduser");
//                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//                user.setName(resultSet.getString("name"));
//                user.setSurname(resultSet.getString("surname"));
//                user.setPatronymic(resultSet.getString("patronymic"));
//                user.setStatus("Не определён.");
//                user.setDiagnosis(resultSet.getString("diagnosis"));
//
//                System.out.println(user);
            }

            //Удаление пользователя
            preparedStatement = worker.getConnection().prepareStatement(DELETE_USER);
            preparedStatement.setInt(1,addedUserID);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID " + addedUserID + " успешно удален!");

            worker.getConnection().close();


            // TODO: ЗАКРЫТИЕ БД ДОЛЖНО БЫТЬ В FINALLY!!!


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

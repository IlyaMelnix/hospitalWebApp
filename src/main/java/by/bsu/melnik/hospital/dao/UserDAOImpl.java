package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The methods in this interface correspond to the CRUD terms of the application.
 */
public class UserDAOImpl implements UserDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

//    private void closeConnection(Connection connection){
//        if (connection == null)
//            return;
//        try {
//            connection.close();
//        } catch (SQLException e) {
//        }
//    }

    // TODO: Дописать метод получения всех пользователей.
    public List<User> findAllUsers(){

        List<User> result = new ArrayList<>();

        String sql = "SELECT * FROM user INNER JOIN STATUS ON user.status_idstatus = status.idstatus";

        Connection connection = null;
        try {


            connection = pool.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){

                // Получить все, что можно, из юзера
                // Создать новые запросы в таблицы Drug, Operation, Procedure на записи с таким же id
                // Если находит - создаём новый объект, добавляем его в список через get..List.add
                // Позакрывать все prepareStatement

                // В книжке: Learn Java for Web - страница 30

                User user = new User();
                user.setIduser(resultSet.getInt("iduser"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPatronymic(resultSet.getString("patronymic"));

                //TODO: Сделать нормальное получение статуса
                user.setStatus("Не определён.");
                user.setDiagnosis(resultSet.getString("diagnosis"));

                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }

        return result;
    }


    public List<User> searchUserByKeyword(String keyword) {
        return null;
    }


    public List<User> findAllByStatus(int status) {
        return null;
    }


    public void insert(User user) {

    }


    public void update(User user) {

    }

    public void delete(int iduser) {

    }
}


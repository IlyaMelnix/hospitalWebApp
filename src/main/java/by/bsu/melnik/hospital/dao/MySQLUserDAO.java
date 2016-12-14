package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {


    // Запрос на получение пользователя по username и password
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM `hospital`.`user` " +
            "INNER JOIN `hospital`.`status` ON `hospital`.`user`.`status_idstatus` = `hospital`.`status`.`idstatus` " +
            "WHERE `hospital`.`user`.`username` = ? AND `hospital`.`user`.`password` = ? ;";

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User searchUserByUsernameAndPassword(String username, String password) {

        // Создание нового пользователя
        User user = null;

        // Подготовка ссылок на объекты
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            // Запрос на получение соединения
            connection = pool.getConnection();

            // Добавление в запрос параметров username и password
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            // Выполнение запроса и получение набора пользователей
            ResultSet resultSet = preparedStatement.executeQuery();

            // Если не найдено пользователей - вернуть null
            if (!resultSet.next()){
                return user;
            }

            // Если найдено несколько пользователей - создать исключение
            int resultSetSize = resultSet.getFetchSize();
            if (resultSetSize > 1){
                throw new DAOException("Найдено более одного пользователя с логином " + username + " и паролем " + password);
            }

            // Если пользователь найден - сохраним его в user
            user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setIdstatus(resultSet.getInt("idstatus"));
            user.setStatus(resultSet.getString("statusName"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setDiagnosis(resultSet.getString("diagnosis"));

            // Создаём новые списки болезней, операций и процедур для этого пользователя.
            // TODO: Создаём новые списки болезней, операций и процедур для этого пользователя.
            // TODO: Создать дао для каждой таблицы
            user.setUserDrugsList(null);
            user.setUserOperationList(null);
            user.setUserProceduresList(null);

            // Возвращаем созданного пользователя
            return user;

        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        return user;
    }

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


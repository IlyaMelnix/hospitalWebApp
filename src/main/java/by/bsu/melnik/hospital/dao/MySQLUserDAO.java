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

    private static final String INSERT_NEW_USER = "INSERT INTO `hospital`.`user` " +
            "(`username`, `password`, `name`, `surname`, `patronymic`, `diagnosis`, `status_idstatus`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String FIND_ADD_USERS = "SELECT * FROM user INNER JOIN STATUS ON user.status_idstatus = status.idstatus;";
    //private static final String FIND_ADD_USERS = "SELECT * FROM user INNER JOIN STATUS ON user.status_idstatus = status.idstatus ORDER BY user.status_idstatus ASC;";
    private static final String DELETE_USER_BY_ID = "delete from user where iduser =?;";
    private static final String DISCHARGE_USER = "UPDATE `hospital`.`user` SET `status_idstatus` = 0 WHERE `iduser` = ?;";
    private ConnectionPool pool = ConnectionPool.getInstance();

    private static DrugDAO drugDAO = new MySQLDrugDAO();
    private static ProcedureDAO procedureDAO = new MySQLProcedureDAO();
    private static OperationDAO operationDAO = new MySQLOperationDAO();

    private User extractUser(ResultSet resultSet) throws SQLException {

        User user = new User();
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
        user.setUserDrugsList(drugDAO.FindUserDrugs(user.getIduser()));
        user.setUserOperationList(operationDAO.FindUserOperations(user.getIduser()));
        user.setUserProceduresList(procedureDAO.FindUserProcedures(user.getIduser()));
        return user;
    }

    @Override
    public User searchUserByUsernameAndPassword(String username, String password) {

        // Создание нового пользователя
        User user = null;

        // Создание объектов
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
            user = extractUser(resultSet);

            // Возвращаем созданного пользователя
            //return user;

        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        } finally {

            // Запрос на получение соединения
            pool.releaseConnection(connection);
        }

        // Возвращаем созданного пользователя
        return user;
    }

    @Override
    public User createNewUser(String username, String password, String name, String surname, String patronymic, String diagnosis, int status) {

        // Создание нового пользователя
        User user = null;

        // Создание объектов
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Добавление пользователя
        try {

            // Запрос на получение соединения
            connection = pool.getConnection();

            // Создание запроса на insert
            preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,surname);
            preparedStatement.setString(5,patronymic);
            preparedStatement.setString(6,diagnosis);
            preparedStatement.setInt(7,status);

            // Выполнение запроса
            preparedStatement.execute();
            System.out.println("Пользователь успешно добавлен!");

            // Проверка и инициализация user
            user = searchUserByUsernameAndPassword(username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return user;
    }

    public List<User> findAllUsers(){

        List<User> users = new ArrayList<>();
        Connection connection = null;

        try {

            connection = pool.getConnection();

            PreparedStatement statement = connection.prepareStatement(FIND_ADD_USERS);
            ResultSet resultSet = statement.executeQuery(FIND_ADD_USERS);

            while(resultSet.next()){

                User user = extractUser(resultSet);
                users.add(user);
                System.out.println("Добавлен пользователь в users! " + user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return users;
    }

    @Override
    public void dischargeUser(int iduser) {

        Connection connection = null;
        try {

            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DISCHARGE_USER);
            statement.setInt(1, iduser);
            int count = statement.executeUpdate();

            // TODO: ДОБАВИТЬ ПРОВЕРКУ НА УДАЛЕНИЕ

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void update(User user) {


    }

    public void delete(int iduser) {

        Connection connection = null;
        try {

            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID);
            statement.setInt(1, iduser);
            int count = statement.executeUpdate();

            // TODO: ДОБАВИТЬ ПРОВЕРКУ НА УДАЛЕНИЕ

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }

    }
}


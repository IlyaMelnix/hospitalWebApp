package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLOperationDAO implements OperationDAO {


    private static final String FIND_OPERATIONS_BY_IDUSER = "SELECT * FROM hospital.operation WHERE hospital.operation.user_iduser = ?;";
    private static final String ADD_OPERATION = "INSERT INTO `hospital`.`operation` (`operationName`, `operationDesc`, `operationDate`, `user_iduser`) VALUES (?, ?, ?, ?);";
    private static final String DELETE_OPERATION_BY_ID = "DELETE FROM `hospital`.`operation` WHERE `hospital`.`operation`.`idoperation` = ?;";

    private ConnectionPool pool = ConnectionPool.getInstance();

    private Operation extractOperation(ResultSet resultSet) throws SQLException {

        Operation operation = new Operation();

        operation.setIdoperation(resultSet.getInt("idoperation"));
        operation.setIduser(resultSet.getInt("user_iduser"));

        operation.setOperationName(resultSet.getString("operationName"));
        operation.setOperationDesc(resultSet.getString("operationDesc"));

        operation.setOperationDate(resultSet.getDate("operationDate"));

        return operation;
    }

    @Override
    public List<Operation> FindUserOperations(int iduser) {

        List<Operation> userOperations = new ArrayList<>();
        Connection connection = null;

        try {

            connection = pool.getConnection();

            PreparedStatement statement = connection.prepareStatement(FIND_OPERATIONS_BY_IDUSER);
            statement.setInt(1, iduser);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Operation operation = extractOperation(resultSet);
                userOperations.add(operation);
                System.out.println("Добавлена операция в users! " + operation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }
        return userOperations;
    }

    @Override
    public boolean addOperation(String operationName, String operationDesc, String operationDate, int iduser) {

        // Создание объектов
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Добавление лекарства
        try {

            // Запрос на получение соединения
            connection = pool.getConnection();

            // Определение даты
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(operationDate);
            java.sql.Date operationDateSQL = new java.sql.Date(parsed.getTime());

            // Создание запроса на insert
            preparedStatement = connection.prepareStatement(ADD_OPERATION);
            preparedStatement.setString(1, operationName);
            preparedStatement.setString(2, operationDesc);
            preparedStatement.setDate(3, operationDateSQL);
            preparedStatement.setInt(4, iduser);

            // Выполнение запроса
            preparedStatement.execute();
            System.out.println("Процедура успешно добавлена!");


        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public void delete(int idoperation) {

        Connection connection = null;
        try {

            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_OPERATION_BY_ID);
            statement.setInt(1, idoperation);
            int count = statement.executeUpdate();

            // TODO: ДОБАВИТЬ ПРОВЕРКУ НА УДАЛЕНИЕ

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

}

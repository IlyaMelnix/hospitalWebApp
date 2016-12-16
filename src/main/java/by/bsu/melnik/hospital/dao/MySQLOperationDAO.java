package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLOperationDAO implements OperationDAO {


    private static final String FIND_OPERATIONS_BY_IDUSER = "SELECT * FROM hospital.operation WHERE hospital.operation.user_iduser = ?;";
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

}

package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLProcedureDAO implements ProcedureDAO {

    private static final String FIND_PROCEDURES_BY_IDUSER = "SELECT * FROM hospital.procedure WHERE hospital.procedure.user_iduser = ?;";
    private static final String ADD_PROCEDURE = "INSERT INTO `hospital`.`procedure` (`procedureName`, `procedureDesc`, `procedureDuration`, `procedureStartDate`, `procedureHowManyTimes`, `user_iduser`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_PROCEDURE_BY_ID = "DELETE FROM `hospital`.`procedure` WHERE `hospital`.`procedure`.`idprocedure` = ?;";
    private ConnectionPool pool = ConnectionPool.getInstance();


    private Procedure extractProcedure(ResultSet resultSet) throws SQLException {

        Procedure procedure = new Procedure();

        procedure.setIdprocedure(resultSet.getInt("idprocedure"));
        procedure.setIduser(resultSet.getInt("user_iduser"));
        procedure.setProcedureDuration(resultSet.getInt("procedureDuration"));
        procedure.setProcedureHowManyTimes(resultSet.getInt("procedureHowManyTimes"));

        procedure.setProcedureName(resultSet.getString("procedureName"));
        procedure.setProcedureDesc(resultSet.getString("procedureDesc"));

        procedure.setProcedureStartDate(resultSet.getDate("procedureStartDate"));

        return procedure;
    }

    @Override
    public List<Procedure> FindUserProcedures(int iduser) {

        List<Procedure> userProcedures = new ArrayList<>();
        Connection connection = null;

        try {

            connection = pool.getConnection();

            PreparedStatement statement = connection.prepareStatement(FIND_PROCEDURES_BY_IDUSER);
            statement.setInt(1, iduser);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Procedure procedure = extractProcedure(resultSet);
                userProcedures.add(procedure);
                System.out.println("Добавлена процедура в users! " + procedure);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }
        return userProcedures;
    }

    @Override
    public boolean addProcedure(String procedureName, String procedureDesc, int procedureDuration, String procedureStartDate, int procedureHowManyTimes, int iduser) {

        // Создание объектов
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Добавление лекарства
        try {

            // Запрос на получение соединения
            connection = pool.getConnection();

            // Определение даты
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(procedureStartDate);
            java.sql.Date procedureStartDateSQL = new java.sql.Date(parsed.getTime());

            // Создание запроса на insert
            preparedStatement = connection.prepareStatement(ADD_PROCEDURE);
            preparedStatement.setString(1, procedureName);
            preparedStatement.setString(2, procedureDesc);
            preparedStatement.setInt(3, procedureDuration);
            preparedStatement.setDate(4, procedureStartDateSQL);
            preparedStatement.setInt(5, procedureHowManyTimes);
            preparedStatement.setInt(6, iduser);

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
    public void delete(int idprocedure) {

        Connection connection = null;
        try {

            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PROCEDURE_BY_ID);
            statement.setInt(1, idprocedure);
            int count = statement.executeUpdate();

            // TODO: ДОБАВИТЬ ПРОВЕРКУ НА УДАЛЕНИЕ

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }

    }
}

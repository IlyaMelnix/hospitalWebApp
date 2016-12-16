package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProcedureDAO implements ProcedureDAO {

    private static final String FIND_PROCEDURES_BY_IDUSER = "SELECT * FROM hospital.procedure WHERE hospital.procedure.user_iduser = ?;";
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
}

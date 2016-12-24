package by.bsu.melnik.hospital.dao.mysql;

import by.bsu.melnik.hospital.dao.ConnectionPool;
import by.bsu.melnik.hospital.dao.DrugDAO;
import by.bsu.melnik.hospital.model.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDrugDAO implements DrugDAO {

    private static final String FIND_DRUGS_BY_IDUSER = "SELECT * FROM hospital.drug WHERE hospital.drug.user_iduser = ?;";
    private static final String ADD_DRUG = "INSERT INTO `hospital`.`drug` (`drugName`, `drugDesc`, `drugDosing`, `user_iduser`) VALUES (?, ?, ?, ?);";
    private static final String DELETE_DRUG_BY_ID = "DELETE FROM `hospital`.`drug` WHERE `hospital`.`drug`.`iddrug` = ?;";

    private ConnectionPool pool = ConnectionPool.getInstance();

    private Drug extractDrug(ResultSet resultSet) throws SQLException {

        Drug drug = new Drug();

        drug.setIddrug(resultSet.getInt("iddrug"));
        drug.setIduser(resultSet.getInt("user_iduser"));
        drug.setDrugName(resultSet.getString("drugName"));
        drug.setDrugDesc(resultSet.getString("drugDesc"));
        drug.setDrugDosing(resultSet.getString("drugDosing"));

        return drug;
    }

    @Override
    public List<Drug> FindUserDrugs(int iduser) {

        List<Drug> userDrugs = new ArrayList<>();
        Connection connection = null;

        try {

            connection = pool.getConnection();

            PreparedStatement statement = connection.prepareStatement(FIND_DRUGS_BY_IDUSER);
            statement.setInt(1, iduser);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Drug drug = extractDrug(resultSet);
                userDrugs.add(drug);
                System.out.println("Добавлено лекарство в users! " + drug);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return userDrugs;
    }

    @Override
    public boolean AddDrug(String drugName, String drugDesc, String drugDosing, int iduser) {

        // Создание нового лекарства
        //Drug drug = null;

        // Создание объектов
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Добавление лекарства
        try {

            // Запрос на получение соединения
            connection = pool.getConnection();

            // Создание запроса на insert
            preparedStatement = connection.prepareStatement(ADD_DRUG);
            preparedStatement.setString(1,drugName);
            preparedStatement.setString(2,drugDesc);
            preparedStatement.setString(3,drugDosing);
            preparedStatement.setInt(4,iduser);

            // Выполнение запроса
            preparedStatement.execute();
            System.out.println("Лекарство успешно добавлено!");

            // Проверка и инициализация user
            //drug =

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public void delete(int iddrug) {
        
        Connection connection = null;
        try {

            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_DRUG_BY_ID);
            statement.setInt(1, iddrug);
            int count = statement.executeUpdate();

            // TODO: ДОБАВИТЬ ПРОВЕРКУ НА УДАЛЕНИЕ

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

}

package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDrugDAO implements DrugDAO {

    private static final String FIND_DRUGS_BY_IDUSER = "SELECT * FROM hospital.drug WHERE hospital.drug.user_iduser = ?;";
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
                System.out.println("Добавлен пользователь в users! " + drug);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            pool.releaseConnection(connection);
        }

        return userDrugs;
    }

}

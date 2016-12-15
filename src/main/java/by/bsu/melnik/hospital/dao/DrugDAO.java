package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Drug;

import java.util.List;

/**
 * Created by ilyah on 15.12.2016.
 */
public interface DrugDAO {

    public List<Drug> FindUserDrugs(int iduser);
}

package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Procedure;

import java.util.List;

/**
 * Created by ilyah on 16.12.2016.
 */
public interface ProcedureDAO {

    public List<Procedure> FindUserProcedures(int iduser);
}

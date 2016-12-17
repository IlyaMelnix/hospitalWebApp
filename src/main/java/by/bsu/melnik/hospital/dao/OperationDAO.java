package by.bsu.melnik.hospital.dao;

import by.bsu.melnik.hospital.model.Operation;

import java.util.List;

public interface OperationDAO {
    public List<Operation> FindUserOperations(int iduser);
    public boolean addOperation(String operationName, String operationDesc, String procedureStartDate, int iduser);
    public void delete(int idoperation);
}

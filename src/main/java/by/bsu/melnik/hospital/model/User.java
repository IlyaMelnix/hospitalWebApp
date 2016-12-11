package by.bsu.melnik.hospital.model;


import java.util.Collections;
import java.util.List;

public class User {

    // FIELDS
    private int iduser;
    private String status;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String diagnosis;
    private List<Drug> userDrugsList;
    private List<Operation> userOperationList;
    private List<Procedure> userProceduresList;


    // CONSTRUCTORS
    public User() {
    }
    public User(int iduser, String status, String username, String password, String name, String surname, String patronymic, String diagnosis, List<Drug> userDrugsList, List<Operation> userOperationList, List<Procedure> userProceduresList) {
        this.iduser = iduser;
        this.status = status;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.diagnosis = diagnosis;
        Collections.copy(this.userDrugsList, userDrugsList);
        Collections.copy(this.userOperationList, userOperationList);
        Collections.copy(this.userProceduresList, userProceduresList);
    }

    // GETTERS AND SETTERS
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Drug> getUserDrugsList() {
        return userDrugsList;
    }
    public void setUserDrugsList(List<Drug> userDrugsList) {
        this.userDrugsList = userDrugsList;
    }

    public List<Operation> getUserOperationList() {
        return userOperationList;
    }
    public void setUserOperationList(List<Operation> userOperationList) {
        this.userOperationList = userOperationList;
    }

    public List<Procedure> getUserProceduresList() {
        return userProceduresList;
    }
    public void setUserProceduresList(List<Procedure> userProceduresList) {
        this.userProceduresList = userProceduresList;
    }


    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronimyc='" + patronymic + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", userDrugsList=" + userDrugsList +
                ", userOperationList=" + userOperationList +
                ", userProceduresList=" + userProceduresList +
                '}';
    }
}

package by.bsu.melnik.hospital.model;


import java.util.Date;

public class Operation {

    // FIELDS
    private int idoperation;
    private int iduser;
    private String operationName;
    private String operationDesc;
    private Date operationDate;

    // CONSTRUCTORS
    public Operation() {
    }
    public Operation(int idoperation, int iduser, String operationName, String operationDesc, Date operationDate) {
        this.idoperation = idoperation;
        this.iduser = iduser;
        this.operationName = operationName;
        this.operationDesc = operationDesc;
        this.operationDate = operationDate;
    }

    // GETTERS AND SETTERS
    public int getIdoperation() {
        return idoperation;
    }
    public void setIdoperation(int idoperation) {
        this.idoperation = idoperation;
    }

    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationDesc() {
        return operationDesc;
    }
    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public Date getOperationDate() {
        return operationDate;
    }
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "idoperation=" + idoperation +
                ", iduser=" + iduser +
                ", operationName='" + operationName + '\'' +
                ", operationDesc='" + operationDesc + '\'' +
                ", operationDate=" + operationDate +
                '}';
    }
}

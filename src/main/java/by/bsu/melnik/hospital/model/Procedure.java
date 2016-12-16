package by.bsu.melnik.hospital.model;

import java.util.Date;


public class Procedure {
    // FIELDS
    private int idprocedure;
    private int iduser;
    private int procedureDuration;
    private int procedureHowManyTimes;
    private String procedureName;
    private String procedureDesc;
    private Date procedureStartDate;

    // CONSTRUCTORS
    public Procedure() {
    }
    public Procedure(int idprocedure, int iduser, int procedureDuration, int procedureHowManyTimes, String procedureName, String procedureDesc, Date procedureStartDate) {
        this.idprocedure = idprocedure;
        this.iduser = iduser;
        this.procedureDuration = procedureDuration;
        this.procedureHowManyTimes = procedureHowManyTimes;
        this.procedureName = procedureName;
        this.procedureDesc = procedureDesc;
        this.procedureStartDate = procedureStartDate;
    }

    // GETTERS AND SETTERS
    public int getIdprocedure() {
        return idprocedure;
    }
    public void setIdprocedure(int idprocedure) {
        this.idprocedure = idprocedure;
    }

    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getProcedureDuration() {
        return procedureDuration;
    }
    public void setProcedureDuration(int procedureDuration) {
        this.procedureDuration = procedureDuration;
    }

    public int getProcedureHowManyTimes() {
        return procedureHowManyTimes;
    }
    public void setProcedureHowManyTimes(int procedureHowManyTimes) {
        this.procedureHowManyTimes = procedureHowManyTimes;
    }

    public String getProcedureName() {
        return procedureName;
    }
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }
    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public Date getProcedureStartDate() {
        return procedureStartDate;
    }
    public void setProcedureStartDate(Date procedureStartDate) {
        this.procedureStartDate = procedureStartDate;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "idprocedure=" + idprocedure +
                ", iduser=" + iduser +
                ", procedureDuration=" + procedureDuration +
                ", procedureHowManyTimes=" + procedureHowManyTimes +
                ", procedureName='" + procedureName + '\'' +
                ", procedureDesc='" + procedureDesc + '\'' +
                ", procedureStartDate=" + procedureStartDate +
                '}';
    }
}

package by.bsu.melnik.hospital.model;


public class Drug {

    // FIELDS
    private int iddrug;
    private int iduser;
    private String drugName;
    private String drugDesc;
    private String drugDosing;

    // CONSTRUCTORS
    public Drug(int iddrug, int iduser, String drugName, String drugDesc, String drugDosing) {
        this.iddrug = iddrug;
        this.iduser = iduser;
        this.drugName = drugName;
        this.drugDesc = drugDesc;
        this.drugDosing = drugDosing;
    }
    public Drug() {
    }

    // GETTERS AND SETTERS
    public int getIddrug() {
        return iddrug;
    }
    public void setIddrug(int iddrug) {
        this.iddrug = iddrug;
    }

    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getDrugName() {
        return drugName;
    }
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugDesc() {
        return drugDesc;
    }
    public void setDrugDesc(String drugDesc) {
        this.drugDesc = drugDesc;
    }

    public String getDrugDosing() {
        return drugDosing;
    }
    public void setDrugDosing(String drugDosing) {
        this.drugDosing = drugDosing;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "iddrug=" + iddrug +
                ", iduser=" + iduser +
                ", drugName='" + drugName + '\'' +
                ", drugDesc='" + drugDesc + '\'' +
                ", drugDosing='" + drugDosing + '\'' +
                '}';
    }
}
